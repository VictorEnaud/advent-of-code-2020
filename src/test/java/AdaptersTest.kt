import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_ADAPTERS_INPUT = File("src/test/resources/DAY_10_DATA.txt").readLines()

internal class AdaptersTest {
    @Test
    fun `should take into account difference between outlent and first adapter and between last adapter and device`() {
        // Given
        val adapters = Adapters(listOf("1"))

        // When
        val oneAndThreeJoltsDifferenceMultiplication = adapters.oneAndThreeJoltsDifferenceMultiplication()

        // Then
        assertThat(oneAndThreeJoltsDifferenceMultiplication).isEqualTo(1)
    }

    @Test
    fun `should take into account all differences of 1 jolt`() {
        // Given
        val adapters = Adapters(listOf("1", "2"))

        // When
        val oneAndThreeJoltsDifferenceMultiplication = adapters.oneAndThreeJoltsDifferenceMultiplication()

        // Then
        assertThat(oneAndThreeJoltsDifferenceMultiplication).isEqualTo(2)
    }

    @Test
    fun `should take into account all differences of 3 jolt`() {
        // Given
        val adapters = Adapters(listOf("1", "2", "5"))

        // When
        val oneAndThreeJoltsDifferenceMultiplication = adapters.oneAndThreeJoltsDifferenceMultiplication()

        // Then
        assertThat(oneAndThreeJoltsDifferenceMultiplication).isEqualTo(4)
    }

    @Test
    fun `should order adapters by order of usage`() {
        // Given
        val adapters = Adapters(listOf("1", "5", "2"))

        // When
        val oneAndThreeJoltsDifferenceMultiplication = adapters.oneAndThreeJoltsDifferenceMultiplication()

        // Then
        assertThat(oneAndThreeJoltsDifferenceMultiplication).isEqualTo(4)
    }

    @Test
    fun `Day 10 - Part 1`() {
        // Given
        val adapters = Adapters(REAL_ADAPTERS_INPUT)

        // When
        val oneAndThreeJoltsDifferenceMultiplication = adapters.oneAndThreeJoltsDifferenceMultiplication()

        // Then
        assertThat(oneAndThreeJoltsDifferenceMultiplication).isEqualTo(1984)
    }

    @Test
    fun `should have only one possible combination when only one number`() {
        // Given
        val adapters = Adapters(listOf("1"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(1)
    }

    @Test
    fun `should have only one possible combination when all adapters are separated by 3 jolts`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "7"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(1)
    }

    @Test
    fun `should have two possible combinations`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "5", "7"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(2)
    }

    @Test
    fun `should have four possible combinations`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "5", "6", "7"))
        // 0 1 4 5 6 7 10
        // 0 1 4 5 7 10
        // 0 1 4 6 7 10
        // 0 1 4 7 10

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(4)
    }

    @Test
    fun `should have not ignore 5 consecutive numbers`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "5", "6", "7", "8"))
        // 0 1 4 5 6 7 8 11
        // 0 1 4 5 6 8 11 NEW
        // 0 1 4 5 7 8 11
        // 0 1 4 5 8 11 NEW
        // 0 1 4 6 7 8 11
        // 0 1 4 6 8 11 NEW
        // 0 1 4 7 8 11

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(7)
    }

    @Test
    fun `should have not ignore 6 consecutive numbers`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "5", "6", "7", "8", "9"))
        // 0 1 4 5 6 7 8 9 12
        // 0 1 4 5 6 8 9 12
        // 0 1 4 5 7 8 9 12
        // 0 1 4 5 8 9 12
        // 0 1 4 6 7 8 9 12
        // 0 1 4 6 7 9 12 NEW
        // 0 1 4 6 8 9 12
        // 0 1 4 6 9 12 NEW
        // 0 1 4 7 8 9 12
        // 0 1 4 7 9 12 NEW

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(10)
    }

    @Test
    fun `should multiply possible combinations`() {
        // Given
        val adapters = Adapters(listOf("1", "4", "5", "6", "9", "10", "11"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(4)
    }

    @Test
    fun `should have eight possible combinations`() {
        // Given
        val adapters = Adapters(listOf("16",
                "10",
                "15",
                "5",
                "1",
                "11",
                "7",
                "19",
                "6",
                "12",
                "4"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(8)
    }

    @Test
    fun `should have 19208 possible combinations`() {
        // Given
        val adapters = Adapters(listOf("28",
                "33",
                "18",
                "42",
                "31",
                "14",
                "46",
                "20",
                "48",
                "47",
                "24",
                "23",
                "49",
                "45",
                "19",
                "38",
                "39",
                "11",
                "1",
                "32",
                "25",
                "35",
                "8",
                "17",
                "7",
                "9",
                "4",
                "2",
                "34",
                "10",
                "3"))

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(19208)
    }

    @Test
    fun `Day 10 - Part 2`() {
        // Given
        val adapters = Adapters(REAL_ADAPTERS_INPUT)

        // When
        val possibleCombinations = adapters.possibleCombinations()

        // Then
        assertThat(possibleCombinations).isEqualTo(3543369523456)
    }
}