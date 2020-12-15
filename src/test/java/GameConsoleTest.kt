import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_CONSOLE_INPUT = File("src/test/resources/DAY_8_DATA.txt").readLines()

internal class GameConsoleTest {
    @Test
    fun `should not update accumulator when instruction is nop`() {
        // Given
        val gameConsole = GameConsole(listOf("nop +10"))

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(0)
    }

    @Test
    fun `should add to accumulator when instruction is acc with plus operator`() {
        // Given
        val gameConsole = GameConsole(listOf("acc +10"))

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(10)
    }

    @Test
    fun `should subtract to accumulator when instruction is acc with minus operator`() {
        // Given
        val gameConsole = GameConsole(listOf("acc -10"))

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(-10)
    }

    @Test
    fun `should jump to another instruction when instruction is jump`() {
        // Given
        val gameConsole = GameConsole(listOf("jmp +2", "acc +10", "acc -10"))

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(-10)
    }

    @Test
    fun `should stop run when instruction is run a second time`() {
        // Given
        val gameConsole = GameConsole(listOf("jmp +2", "acc +10", "acc -10", "jmp -1", "acc +25"))

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(-10)
    }

    @Test
    fun `Day 8 - part 1`() {
        // Given
        val gameConsole = GameConsole(REAL_CONSOLE_INPUT)

        // When
        val finalAccumulatorValue = gameConsole.run()

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(1563)
    }

    @Test
    fun `Day 8 - part 2`() {
        // Given
        val jmpIndexes = REAL_CONSOLE_INPUT.mapIndexedNotNull { index: Int, instruction: String ->
            if (instruction.contains("jmp")) index else null
        }
        val nopIndexes = REAL_CONSOLE_INPUT.mapIndexedNotNull { index: Int, instruction: String ->
            if (instruction.contains("nop")) index else null
        }
        var infiniteLoop = true
        var finalAccumulatorValue = 0
        var currentIndex = 0

        // When
        while (infiniteLoop) {
            try {
                infiniteLoop = false
                val newInstruction = REAL_CONSOLE_INPUT[jmpIndexes[currentIndex]].replace("jmp", "nop")
                val mutableInstructions = REAL_CONSOLE_INPUT.toMutableList()
                mutableInstructions[jmpIndexes[currentIndex]] = newInstruction
                val gameConsole = GameConsole(mutableInstructions)
                finalAccumulatorValue = gameConsole.runPorcelain()
            } catch (exception: GameConsole.InfiniteLoopException) {
                infiniteLoop = true
                currentIndex++
            }
        }

        // Then
        assertThat(finalAccumulatorValue).isEqualTo(767)
    }
}