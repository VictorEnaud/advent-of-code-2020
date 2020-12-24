import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_SEATS_INPUT = File("src/test/resources/DAY_11_DATA.txt").readText()

internal class SeatsLayoutTest {
    @Test
    fun `should not fill floor tiles`() {
        // Given
        val seatsLayout = SeatsLayout("..")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(0)
    }

    @Test
    fun `should fill all empty seats with no adjacent occupied seat`() {
        // Given
        val seatsLayout = SeatsLayout("LL")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(2)
    }

    @Test
    fun `should fill all empty seats with no adjacent occupied seat on all lines`() {
        // Given
        val seatsLayout = SeatsLayout("LL\nLL")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(4)
    }

    @Test
    fun `should not occupy seat if adjacent seat is occupied`() {
        // Given
        val seatsLayout = SeatsLayout("L#")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the right`() {
        // Given
        val seatsLayout = SeatsLayout("L.#")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the left`() {
        // Given
        val seatsLayout = SeatsLayout("#.L")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight above`() {
        // Given
        val seatsLayout = SeatsLayout("#\n.\nL")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight under`() {
        // Given
        val seatsLayout = SeatsLayout("L\n.\n#")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the upper left diagonal`() {
        // Given
        val seatsLayout = SeatsLayout(
            "#....\n" +
                    ".....\n" +
                    "..L..\n" +
                    ".....\n" +
                    "....."
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the upper right diagonal`() {
        // Given
        val seatsLayout = SeatsLayout(
            "....#\n" +
                    ".....\n" +
                    "..L..\n" +
                    ".....\n" +
                    "....."
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the lower left diagonal`() {
        // Given
        val seatsLayout = SeatsLayout(
            ".....\n" +
                    ".....\n" +
                    "..L..\n" +
                    ".....\n" +
                    "#...."
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should consider occupied seats in sight on the lower right diagonal`() {
        // Given
        val seatsLayout = SeatsLayout(
            ".....\n" +
                    ".....\n" +
                    "..L..\n" +
                    ".....\n" +
                    "....#"
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should not consider occupied seats blocked by another seat`() {
        // Given
        val seatsLayout = SeatsLayout("LL.#")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(2)
    }

    @Test
    fun `should not occupy seat if adjacent seat on next line is occupied`() {
        // Given
        val seatsLayout = SeatsLayout("LL\n#L")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(1)
    }

    @Test
    fun `should not empty seat if it is adjacent to one occupied seat`() {
        // Given
        val seatsLayout = SeatsLayout("##")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(2)
    }

    @Test
    fun `should not empty seat if it is adjacent to two occupied seats`() {
        // Given
        val seatsLayout = SeatsLayout("###")

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(3)
    }

    @Test
    fun `should not empty seat if it is adjacent to three occupied seats`() {
        // Given
        val seatsLayout = SeatsLayout(
            "##\n" +
                    "##"
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(4)
    }

    @Test
    fun `should not empty seat if it is adjacent to four occupied seats`() {
        // Given
        val seatsLayout = SeatsLayout(
            "###\n" +
                    "#L#"
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(5)
    }

    @Test
    fun `should empty all seats adjacent to five or more occupied seats`() {
        // Given
        val seatsLayout = SeatsLayout(
            "###\n" +
                    "###"
        )

        // When
        seatsLayout.fill()

        // Then
        val occupiedSeats = seatsLayout.occupiedSeats()
        assertThat(occupiedSeats).isEqualTo(4)
    }

    @Test
    fun `should stabilize at 26 seats`() {
        // Given
        val seatsLayout = SeatsLayout(
            "L.LL.LL.LL\n" +
                    "LLLLLLL.LL\n" +
                    "L.L.L..L..\n" +
                    "LLLL.LL.LL\n" +
                    "L.LL.LL.LL\n" +
                    "L.LLLLL.LL\n" +
                    "..L.L.....\n" +
                    "LLLLLLLLLL\n" +
                    "L.LLLLLL.L\n" +
                    "L.LLLLL.LL"
        )
        var lastOccupiedSeats = 0
        var stabilized = false

        // When
        while (!stabilized) {
            seatsLayout.fill()
            if (seatsLayout.occupiedSeats() == lastOccupiedSeats) {
                stabilized = true
            } else {
                lastOccupiedSeats = seatsLayout.occupiedSeats()
            }
        }

        // Then
        assertThat(lastOccupiedSeats).isEqualTo(26)
    }

    @Test
    fun `Day 11`() {
        // Given
        val seatsLayout = SeatsLayout(REAL_SEATS_INPUT)
        var lastOccupiedSeats = 0
        var stabilized = false

        // When
        while (!stabilized) {
            seatsLayout.fill()
            if (seatsLayout.occupiedSeats() == lastOccupiedSeats) {
                stabilized = true
            } else {
                lastOccupiedSeats = seatsLayout.occupiedSeats()
            }
        }

        // Then
        assertThat(lastOccupiedSeats).isEqualTo(2121)
    }
}