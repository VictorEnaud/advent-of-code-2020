import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_PASSWORDS_AND_VALIDATIONS = File("src/test/resources/DAY_2_DATA.txt").readLines()

internal class PasswordsTest {
    @Test
    fun `password should be valid when it contains the requested letter only once at the first requested position`() {
        // Given
        val passwords = Passwords(listOf("1-3 a: adbcde"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(1)
    }

    @Test
    fun `password should be valid when it contains the requested letter only once at the second requested position`() {
        // Given
        val passwords = Passwords(listOf("1-3 b: adbcde"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(1)
    }

    @Test
    fun `password should not be valid when it does not contain the requested letter at any of the requested positions`() {
        // Given
        val passwords = Passwords(listOf("1-4 b: cbefg"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(0)
    }

    @Test
    fun `password should not be valid when it contains the requested letter at both requested positions`() {
        // Given
        val passwords = Passwords(listOf("2-3 b: abbefg"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(0)
    }

    @Test
    fun `password should be valid when it contains the requested letter at the first position and is shorter than requested second position`() {
        // Given
        val passwords = Passwords(listOf("2-20 b: bbbbbbbbbbb"))

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(1)
    }

    @Test
    fun `day 2 - part 1`() {
        // Given
        println("Working Directory = " + System.getProperty("user.dir"));
        val passwords = Passwords(REAL_PASSWORDS_AND_VALIDATIONS)

        // When
        val numberOfValidPasswords = passwords.validPasswords()

        // Then
        assertThat(numberOfValidPasswords).isEqualTo(275)
    }
}