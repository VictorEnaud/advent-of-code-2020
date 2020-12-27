import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemoryGameTest {
    @Test
    fun `first spoken number should be the first starting number`() {
        // Given
        val memoryGame = MemoryGame(listOf(1))

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(1)
    }

    @Test
    fun `spoken number should be 0 if previous number was spoken for the first time`() {
        // Given
        val memoryGame = MemoryGame(listOf(1))
        memoryGame.nextSpokenNumber()

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(0)
    }

    @Test
    fun `spoken number should be 1 if previous number was spoken 1 turn before`() {
        // Given
        val memoryGame = MemoryGame(listOf(1))
        memoryGame.nextSpokenNumber()
        memoryGame.nextSpokenNumber()
        memoryGame.nextSpokenNumber()

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(1)
    }

    @Test
    fun `spoken number should be 3 if previous number was spoken 3 turn before`() {
        // Given
        val memoryGame = MemoryGame(listOf(1))
        memoryGame.nextSpokenNumber()
        memoryGame.nextSpokenNumber()
        memoryGame.nextSpokenNumber()
        memoryGame.nextSpokenNumber()

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(3)
    }

    @Test
    fun `Example 1 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(1,3,2))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(1)
    }

    @Test
    fun `Example 2 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(2,1,3))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(10)
    }

    @Test
    fun `Example 3 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(1,2,3))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(27)
    }

    @Test
    fun `Example 4 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(2,3,1))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(78)
    }

    @Test
    fun `Example 5 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(3,2,1))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(438)
    }

    @Test
    fun `Example 6 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(3,1,2))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(1836)
    }

    @Test
    fun `Day 15 - Part 1`() {
        // Given
        val memoryGame = MemoryGame(listOf(1,12,0,20,8,16))
        for (i in 1..2019) {
            memoryGame.nextSpokenNumber()
        }

        // When
        val nextSpokenNumber = memoryGame.nextSpokenNumber()

        // Then
        assertThat(nextSpokenNumber).isEqualTo(273)
    }
}