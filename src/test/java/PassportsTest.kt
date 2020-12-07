import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

val REAL_PASSPORTS = File("src/test/resources/DAY_4_DATA.txt").readLines()

internal class PassportsTest {
    @Test
    fun `passport should be valid when containing all required fields`() {
        // Given
        val passports =
            Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(1)
    }

    @Test
    fun `passport should be valid when containing all required fields on different lines`() {
        // Given
        val passports =
            Passports(listOf("ecl:gry pid:860033327 eyr:2020", "hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(1)
    }

    @Nested
    inner class BirthYear {
        @Test
        fun `passport should not be valid when missing birth year`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when birth year is before 1920`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1919 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when birth year is after 2002`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:2003 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should be valid when birth year is 1920`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1920 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when birth year is 2002`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:2002 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }
    }

    @Nested
    inner class IssueYear {
        @Test
        fun `passport should not be valid when missing issue year`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when issue year is before 2010`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1929 iyr:2009 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when issue year is after 2020`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1929 iyr:2021 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should be valid when issue year is 2010`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1929 iyr:2010 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when issue year is 2020`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1929 iyr:2020 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }
    }

    @Nested
    inner class ExpirationYear {
        @Test
        fun `passport should not be valid when missing expiration year`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when expiration year is before 2020`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2019 hcl:#fffffd byr:1929 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when expiration year is after 2030`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2031 hcl:#fffffd byr:1929 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should be valid when expiration year is 2020`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1929 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when expiration year is 2030`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2030 hcl:#fffffd byr:1929 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }
    }

    @Nested
    inner class Height {
        @Test
        fun `passport should not be valid when missing height`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when height is lower than 150cm`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:149cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when height is lower than 59in`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:58in"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when height is higher than 193cm`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:194cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when height is higher than 76in`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:77in"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should be valid when height is 150cm`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:150cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when height is 59in`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:59in"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when height is 193cm`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:193cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when height is 76in`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:76in"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should not be valid when height is not in inch or centimeters`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183dm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }
    }

    @Nested
    inner class HairColor {
        @Test
        fun `passport should not be valid when missing hair color`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when hair color doesnt start with '#'`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when hair color does not end with six characters`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffff byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when hair color contains other than 0-9 and a-f`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffg byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }
    }

    @Nested
    inner class EyeColor {
        @Test
        fun `passport should not be valid when missing eye color`() {
            // Given
            val passports = Passports(listOf("pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }


        @ParameterizedTest
        @ValueSource(strings = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"])
        fun `passport should be valid when eye color is`(color: String) {
            // Given
            val passports = Passports(listOf("ecl:$color pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }

        @Test
        fun `passport should be valid when eye color is not valid`() {
            // Given
            val passports = Passports(listOf("ecl:wht pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }
    }

    @Nested
    inner class PassportId {
        @Test
        fun `passport should not be valid when missing passport id`() {
            // Given
            val passports = Passports(listOf("ecl:gry eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when passport id has more than 9 characters`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:9860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when passport id has less than 9 characters`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:0033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should not be valid when passport id countain non-numbers`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:86aa33327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(0)
        }

        @Test
        fun `passport should be valid when passport id begin with 0s`() {
            // Given
            val passports =
                Passports(listOf("ecl:gry pid:000383327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }
    }

    @Nested
    inner class CountryId {
        @Test
        fun `passport should be valid when missing country id`() {
            // Given
            val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 hgt:183cm"))

            // When
            val numberOfValidPassports = passports.validPassports()

            // Then
            assertThat(numberOfValidPassports).isEqualTo(1)
        }
    }

    @Test
    fun `should validate multiple passports at once`() {
        // Given
        val passports = Passports(
            listOf(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
                "",
                "ecl:gry pid:860033327 eyr:2020",
                "hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
            )
        )

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(2)
    }

    @Test
    fun `Day 4`() {
        // Given
        val passports = Passports(REAL_PASSPORTS)

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(153)
    }

}