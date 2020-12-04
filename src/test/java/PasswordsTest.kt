import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_PASSWORDS_AND_VALIDATIONS = File("src/test/resources/DAY_2_DATA.txt").readLines()

internal class PasswordsTest {
    @Test
    internal fun `password should be valid when it contains the requested letter with the requested occurence`() {
        // Given
        val passwords = Passwords(listOf("1-3 a: aaabcde"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(1)
    }

    @Test
    internal fun `password should not be valid when it does not contain the requested letter`() {
        // Given
        val passwords = Passwords(listOf("1-3 b: cdefg"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(0)
    }

    @Test
    internal fun `password should not be valid when it does not contains enough occurences of requested letter`() {
        // Given
        val passwords = Passwords(listOf("2-3 b: bcdefg"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(0)
    }

    @Test
    internal fun `password should not be valid when it contains too much occurences of requested letter`() {
        // Given
        val passwords = Passwords(listOf("2-10 b: bbbbbbbbbbb"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(0)
    }

    @Test
    internal fun `day 2 - part 1`() {
        // Given
        println("Working Directory = " + System.getProperty("user.dir"));
        val passwords = Passwords(REAL_PASSWORDS_AND_VALIDATIONS)

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(546)
    }
}