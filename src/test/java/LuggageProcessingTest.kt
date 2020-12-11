import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

val REAL_LUGGAGE_RULES = File("src/test/resources/DAY_7_DATA.txt").readLines()

internal class LuggageProcessingTest {
    @Nested
    inner class NumberOfBagsContainingColor {
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
        fun `should return 2 when a bag contains a bag that can contain given bag color`() {
            // Given
            val luggageProcessing = LuggageProcessing(
                listOf(
                    "bright white bags contain 1 shiny gold bag.",
                    "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
                )
            )

            // When
            val numberOfBagsContainingColor = luggageProcessing.numberOfBagsContainingColor("shiny gold")

            // Then
            assertThat(numberOfBagsContainingColor).isEqualTo(2)
        }

        @Test
        fun `should return 3 when a bag contains a bag that contains a bag that can contain given bag color`() {
            // Given
            val luggageProcessing = LuggageProcessing(
                listOf(
                    "bright white bags contain 1 shiny gold bag.",
                    "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                    "dotted black bags contain 1 dark orange bag.",
                )
            )

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

    @Nested
    inner class NumberOfBagsContained {
        @Test
        fun `should return 0 when given bag color contains no bag`() {
            // Given
            val luggageProcessing = LuggageProcessing(listOf("shiny gold bags contain no other bags."))

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(0)
        }

        @Test
        fun `should count number of bags contained`() {
            // Given
            val luggageProcessing = LuggageProcessing(listOf("shiny gold bags contain 2 muted yellow bags."))

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(2)
        }

        @Test
        fun `should should also count number of bags contained by contained bags`() {
            // Given
            val luggageProcessing = LuggageProcessing(
                listOf(
                    "shiny gold bags contain 1 bright white bag.",
                    "bright white bags contain 2 muted yellow bags."
                )
            )

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(3)
        }

        @Test
        fun `should count bags contained by all contained bags`() {
            // Given
            val luggageProcessing = LuggageProcessing(
                listOf(
                    "shiny gold bags contain 1 bright white bag, 1 dark orange bag.",
                    "bright white bags contain 2 muted yellow bags.",
                    "dark orange bags contain 1 muted yellow bags."
                )
            )

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(5)
        }

        @Test
        fun `should multiply bags in contained bags by number of times this bag is contained`() {
            // Given
            val luggageProcessing = LuggageProcessing(
                listOf(
                    "shiny gold bags contain 2 bright white bag, 1 dark orange bag.",
                    "bright white bags contain 2 muted yellow bags.",
                    "dark orange bags contain 1 muted yellow bags."
                )
            )

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(8)
        }

        @Test
        fun `DAY 7 - Part 2`() {
            // Given
            val luggageProcessing = LuggageProcessing(REAL_LUGGAGE_RULES)

            // When
            val numberOfBagsContainedBy = luggageProcessing.numberOfBagsContainedBy("shiny gold")

            // Then
            assertThat(numberOfBagsContainedBy).isEqualTo(85324)
        }
    }
}