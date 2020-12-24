import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_BOAT_INPUT = File("src/test/resources/DAY_12_DATA.txt").readLines()

internal class BoatTest {
    @Test
    fun `should move boat north when instruction is N`() {
        // Given
        val boat = Boat(listOf("N10"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(10)
    }

    @Test
    fun `should move boat south when instruction is S`() {
        // Given
        val boat = Boat(listOf("N10", "S5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(5)
    }

    @Test
    fun `should move boat east when instruction is E`() {
        // Given
        val boat = Boat(listOf("N10", "S5", "E15"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(20)
    }

    @Test
    fun `should move boat west when instruction is W`() {
        // Given
        val boat = Boat(listOf("N10", "S5", "E15", "W7"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(13)
    }

    @Test
    fun `boat should face east at first`() {
        // Given
        val boat = Boat(listOf("W10", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(5)
    }

    @Test
    fun `boat should turn to the right when instruction is R`() {
        // Given
        val boat = Boat(listOf("N10", "R90", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(5)
    }

    @Test
    fun `boat should go in the same direction when rotation of 360 degrees to the right`() {
        // Given
        val boat = Boat(listOf("W10", "R360", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(5)
    }

    @Test
    fun `boat should go in the same direction when rotation of 360 degrees to the left`() {
        // Given
        val boat = Boat(listOf("W10", "L360", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(5)
    }

    @Test
    fun `boat should turn to the left when instruction is L`() {
        // Given
        val boat = Boat(listOf("N10", "L90", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(15)
    }

    @Test
    fun `should have manhattan distance of 25`() {
        // Given
        val boat = Boat(listOf("F10",
                "N3",
                "F7",
                "R90",
                "F11"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(25)
    }

    @Test
    fun `Day 12 - Part 1`() {
        // Given
        val boat = Boat(REAL_BOAT_INPUT)

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(796)
    }
}