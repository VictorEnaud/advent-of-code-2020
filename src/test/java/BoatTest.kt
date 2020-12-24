import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_BOAT_INPUT = File("src/test/resources/DAY_12_DATA.txt").readLines()

internal class BoatTest {
    @Test
    fun `should have waypoint 10 east 1 north at the beginning`() {
        // Given
        val boat = Boat(listOf("F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(55)
    }

    @Test
    fun `should move the waypoint north when instruction is N`() {
        // Given
        val boat = Boat(listOf("N10", "F1"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(21)
    }

    @Test
    fun `should move the waypoint south when instruction is S`() {
        // Given
        val boat = Boat(listOf("S5", "F1"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(14)
    }

    @Test
    fun `should move the waypoint east when instruction is E`() {
        // Given
        val boat = Boat(listOf("E15", "F1"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(26)
    }

    @Test
    fun `should move the waypoint west when instruction is W`() {
        // Given
        val boat = Boat(listOf("W7", "F1"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(4)
    }

    @Test
    fun `boat should turn to the right when instruction is R`() {
        // Given
        val boat = Boat(listOf("N10", "R90", "F5"))

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(105)
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
        assertThat(boat.manhattanDistance()).isEqualTo(105)
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
        assertThat(boat.manhattanDistance()).isEqualTo(286)
    }

    @Test
    fun `Day 12`() {
        // Given
        val boat = Boat(REAL_BOAT_INPUT)

        // When
        boat.move()

        // Then
        assertThat(boat.manhattanDistance()).isEqualTo(39446)
    }
}