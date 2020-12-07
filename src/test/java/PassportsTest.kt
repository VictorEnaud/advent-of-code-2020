import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_PASSPORTS = File("src/test/resources/DAY_4_DATA.txt").readLines()

internal class PassportsTest {
    @Test
    fun `passport should be valid when containing all required fields`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(1)
    }

    @Test
    fun `passport should be valid when containing all required fields on different lines`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020", "hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(1)
    }

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
    fun `passport should not be valid when missing issue year`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(0)
    }

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
    fun `passport should not be valid when missing height`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(0)
    }

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
    fun `passport should not be valid when missing eye color`() {
        // Given
        val passports = Passports(listOf("pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(0)
    }

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
    fun `passport should be valid when missing country id`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(1)
    }

    @Test
    fun `should validate multiple passports at once`() {
        // Given
        val passports = Passports(listOf("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm", "", "ecl:gry pid:860033327 eyr:2020", "hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"))

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(2)
    }

    @Test
    fun `Day 4 Part 1`() {
        // Given
        val passports = Passports(REAL_PASSPORTS)

        // When
        val numberOfValidPassports = passports.validPassports()

        // Then
        assertThat(numberOfValidPassports).isEqualTo(260)
    }

}