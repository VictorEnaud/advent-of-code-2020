import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_CRYPTOLOGY_INPUT = File("src/test/resources/DAY_9_DATA.txt").readLines()

internal class CryptologyTest {
    @Test
    fun `should return no number when number is sum of the two previous`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(2)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(null)
    }

    @Test
    fun `should return first invalid number when number is not the sum of the two previous`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "4", "8"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(2)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(4)
    }

    @Test
    fun `should return no number when number is the sum of the two previous after firsts`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3", "5"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(2)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(null)
    }

    @Test
    fun `should return first invalid number when number is not the sum of the two previous after firsts`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3", "5", "7"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(2)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(7)
    }

    @Test
    fun `should return no number when number is the sum of two numbers among the numbers to consider`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3", "4"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(3)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(null)
    }

    @Test
    fun `Example - Part 1`() {
        // Given
        val cryptology = Cryptology(listOf("35",
                "20",
                "15",
                "25",
                "47",
                "40",
                "62",
                "55",
                "65",
                "95",
                "102",
                "117",
                "150",
                "182",
                "127",
                "219",
                "299",
                "277",
                "309",
                "576"))

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(5)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(127)
    }

    @Test
    fun `Day 9 - Part 1`() {
        // Given
        val cryptology = Cryptology(REAL_CRYPTOLOGY_INPUT)

        // When
        val firstInvalidNumber = cryptology.firstInvalidNumber(25)

        // Then
        assertThat(firstInvalidNumber).isEqualTo(25918798)
    }

    @Test
    fun `should return smallest and largest number in the range that sum to given number`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3", "4"))

        // When
        val rangeExtremes = cryptology.extremesOfSumRangeForNumber(3)

        // Then
        assertThat(rangeExtremes.first).isEqualTo(1)
        assertThat(rangeExtremes.second).isEqualTo(2)
    }

    @Test
    fun `should find a continuous range which sum to given number`() {
        // Given
        val cryptology = Cryptology(listOf("1", "2", "3", "5"))

        // When
        val rangeExtremes = cryptology.extremesOfSumRangeForNumber(5)

        // Then
        assertThat(rangeExtremes.first).isEqualTo(2)
        assertThat(rangeExtremes.second).isEqualTo(3)
    }

    @Test
    fun `Example - Part 2`() {
        // Given
        val cryptology = Cryptology(listOf("35",
            "20",
            "15",
            "25",
            "47",
            "40",
            "62",
            "55",
            "65",
            "95",
            "102",
            "117",
            "150",
            "182",
            "127",
            "219",
            "299",
            "277",
            "309",
            "576"))
        val firstInvalidNumber = cryptology.firstInvalidNumber(5)

        // When
        val rangeExtremes = cryptology.extremesOfSumRangeForNumber(firstInvalidNumber!!)

        // Then
        assertThat(rangeExtremes.first!! + rangeExtremes.second!!).isEqualTo(62)
    }

    @Test
    fun `Day 9 - Part 2`() {
        // Given
        val cryptology = Cryptology(REAL_CRYPTOLOGY_INPUT)
        val firstInvalidNumber = cryptology.firstInvalidNumber(25)

        // When
        val rangeExtremes = cryptology.extremesOfSumRangeForNumber(firstInvalidNumber!!)

        // Then
        assertThat(rangeExtremes.first!! + rangeExtremes.second!!).isEqualTo(3340942)
    }
}