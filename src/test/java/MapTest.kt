import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_MAP = File("src/test/resources/DAY_3_DATA.txt").readLines()

internal class MapTest {
    @Test
    fun `should not encounter tree on path when there is none`() {
        // Given
        val map = Map(listOf("..##.......", "#...#...#.."))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(0)
    }

    @Test
    fun `should encounter one tree on next move when there is one`() {
        // Given
        val map = Map(listOf("..##.......", "#...#...#..", ".#....#..#."))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(1)
    }

    @Test
    fun `should encounter two trees on a larger map`() {
        // Given
        val map = Map(listOf("..##.........",
                "#...#...#..#.",
                ".#....#..#..#",
                "..#.#...#.#..",
                ".....##..#..#"))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(2)
    }

    @Test
    fun `should encounter seven trees on test map`() {
        // Given
        val map = Map(listOf("..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(7)
    }

    @Test
    fun `day 3 - part 1`() {
        // Given
        val map = Map(REAL_MAP)

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(254)
    }
}