import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_MAP = File("src/test/resources/DAY_3_DATA.txt").readLines()

internal class MapTest {
    @Test
    fun `should not encounter tree on path when there is none`() {
        // Given
        val map = Map(3, 1, listOf("..##.......", "#...#...#.."))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(0)
    }

    @Test
    fun `should encounter one tree on next move when there is one`() {
        // Given
        val map = Map(3, 1, listOf("..##.......", "#...#...#..", ".#....#..#."))

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(1)
    }

    @Test
    fun `should encounter two trees on a larger map`() {
        // Given
        val map = Map(
            3, 1, listOf(
                "..##.........",
                "#...#...#..#.",
                ".#....#..#..#",
                "..#.#...#.#..",
                ".....##..#..#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(2)
    }

    @Test
    fun `should encounter two trees on test map with a movement of right 1 down 1`() {
        // Given
        val map = Map(
            1, 1, listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(2)
    }

    @Test
    fun `should encounter three trees on test map with a movement of right 5 down 1`() {
        // Given
        val map = Map(
            5, 1, listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(3)
    }

    @Test
    fun `should encounter four trees on test map with a movement of right 7 down 1`() {
        // Given
        val map = Map(
            7, 1, listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(4)
    }

    @Test
    fun `should encounter two trees on test map with a movement of right 1 down 2`() {
        // Given
        val map = Map(
            1, 2, listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(2)
    }

    @Test
    fun `should encounter seven trees on test map with a movement of right 1 down 3`() {
        // Given
        val map = Map(
            3, 1, listOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#"
            )
        )

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(7)
    }

    @Test
    fun `day 3 - part 1`() {
        // Given
        val map = Map(3, 1, REAL_MAP)

        // When
        val numberOfTreesEncountered = map.numberOfTreesInTobogganPath()

        // Then
        assertThat(numberOfTreesEncountered).isEqualTo(254)
    }

    @Test
    fun `day 3 - part 2`() {
        // Given
        val right1Down1Map = Map(1, 1, REAL_MAP)
        val right3Down1Map = Map(3, 1, REAL_MAP)
        val right5Down1Map = Map(5, 1, REAL_MAP)
        val right7Down1Map = Map(7, 1, REAL_MAP)
        val right1Down2Map = Map(1, 2, REAL_MAP)
        val right1Down1NumberOfTreesEncountered = right1Down1Map.numberOfTreesInTobogganPath()
        val right3Down1NumberOfTreesEncountered = right3Down1Map.numberOfTreesInTobogganPath()
        val right5Down1NumberOfTreesEncountered = right5Down1Map.numberOfTreesInTobogganPath()
        val right7Down1NumberOfTreesEncountered = right7Down1Map.numberOfTreesInTobogganPath()
        val right1Down2NumberOfTreesEncountered = right1Down2Map.numberOfTreesInTobogganPath()

        // When
        val treesMultiplication =
            right1Down1NumberOfTreesEncountered * right3Down1NumberOfTreesEncountered * right5Down1NumberOfTreesEncountered * right7Down1NumberOfTreesEncountered * right1Down2NumberOfTreesEncountered

        // Then
        assertThat(treesMultiplication).isEqualTo(1666768320)
    }
}