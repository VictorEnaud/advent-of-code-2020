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
}