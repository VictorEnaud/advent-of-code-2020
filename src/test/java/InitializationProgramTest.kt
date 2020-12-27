import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_INITIALIZATION_INPUT = File("src/test/resources/DAY_14_DATA.txt").readLines()

internal class InitializationProgramTest {
    @Test
    fun `should return the sum of values in memory`() {
        // Given
        val initializationProgram = InitializationProgramV1()
        initializationProgram.saveInMemoryAtAddress(7, 38)

        // When
        initializationProgram.saveInMemoryAtAddress(8, 42)

        // Then
        assertThat(initializationProgram.sumOfMemoryValues()).isEqualTo(80)
    }

    @Test
    fun `should replace value when assigned to same memory address`() {
        // Given
        val initializationProgram = InitializationProgramV1()
        initializationProgram.saveInMemoryAtAddress(7, 38)

        // When
        initializationProgram.saveInMemoryAtAddress(7, 42)

        // Then
        assertThat(initializationProgram.sumOfMemoryValues()).isEqualTo(42)
    }

    @Test
    fun `should apply bitmask to added value`() {
        // Given
        val initializationProgram = InitializationProgramV1()
        initializationProgram.updateBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1")

        // When
        initializationProgram.saveInMemoryAtAddress(7, 0)

        // Then
        assertThat(initializationProgram.sumOfMemoryValues()).isEqualTo(1)
    }

    @Test
    fun `Day 14 - Part 1`() {
        // Given
        val initializationProgram = InitializationProgramV1()

        // When
        REAL_INITIALIZATION_INPUT.forEach {
            val command = it.split(" = ")

            if (command[0] == "mask") {
                initializationProgram.updateBitmask(command[1])
            } else {
                val address = command[0].drop(4).dropLast(1)
                initializationProgram.saveInMemoryAtAddress(address.toInt(), command[1].toLong())
            }
        }

        // Then
        assertThat(initializationProgram.sumOfMemoryValues()).isEqualTo(15172047086292)
    }
}