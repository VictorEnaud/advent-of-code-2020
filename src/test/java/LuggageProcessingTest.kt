import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_LUGGAGE_RULES = File("src/test/resources/DAY_7_DATA.txt").readLines()

internal class LuggageProcessingTest {
    @Test
    fun `should return 0 when no bags can contain given bag color`() {
        // Given
        val luggageProcessing = LuggageProcessing(listOf("light red bags contain no other bags."))

        // When
        val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

        // Then
        assertThat(numberOfBagsContainingColor).isEqualTo(0)
    }

    @Test
    fun `should return 1 when one bag can contain given bag color`() {
        // Given
        val luggageProcessing = LuggageProcessing(listOf("bright white bags contain 1 shiny gold bag."))

        // When
        val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

        // Then
        assertThat(numberOfBagsContainingColor).isEqualTo(1)
    }

    @Test
    fun `should return 2  when a bag contains a bag that can contain given bag color`() {
        // Given
        val luggageProcessing = LuggageProcessing(listOf("bright white bags contain 1 shiny gold bag.", "dark orange bags contain 3 bright white bags, 4 muted yellow bags."))

        // When
        val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

        // Then
        assertThat(numberOfBagsContainingColor).isEqualTo(2)
    }

    @Test
    fun `should return 3 when a bag contains a bag that contains a bag that can contain given bag color`() {
        // Given
        val luggageProcessing = LuggageProcessing(listOf("bright white bags contain 1 shiny gold bag.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "dotted black bags contain 1 dark orange bag.",
        ))

        // When
        val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

        // Then
        assertThat(numberOfBagsContainingColor).isEqualTo(3)
    }

    @Test
    fun `DAY 7 - Part 1`() {
        // Given
        val luggageProcessing = LuggageProcessing(REAL_LUGGAGE_RULES)

        // When
        val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

        // Then
        assertThat(numberOfBagsContainingColor).isEqualTo(197)
    }
}