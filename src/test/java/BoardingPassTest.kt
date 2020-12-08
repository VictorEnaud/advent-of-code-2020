import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_BOARDING_PASSES = File("src/test/resources/DAY_5_DATA.txt").readLines()

internal class BoardingPassTest {

    @Test
    fun `seat should be between row 0 and 63 when first direction letter is F`() {
        // Given
        val boardingPass = BoardingPass("FFFBBBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isBetween(0, 63)
    }

    @Test
    fun `seat should be between row 32 and 63 when first direction letter is F and second is B`() {
        // Given
        val boardingPass = BoardingPass("FBFBBBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isBetween(32, 63)
    }

    @Test
    fun `seat should be between row 64 and 127 when first direction letter is B`() {
        // Given
        val boardingPass = BoardingPass("BFFBBBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isBetween(64, 127)
    }

    @Test
    fun `seat should be row number 1 when its directions are "FFFFFFB"`() {
        // Given
        val boardingPass = BoardingPass("FFFFFFBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isEqualTo(1)
    }

    @Test
    fun `seat should be row number 70 when its directions are "BFFFBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("BFFFBBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isEqualTo(70)
    }

    @Test
    fun `seat should be row number 14 when its directions are "FFFBBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("FFFBBBFRRR")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isEqualTo(14)
    }

    @Test
    fun `seat should be row number 102 when its directions are "BBFFBBFRLL"`() {
        // Given
        val boardingPass = BoardingPass("BBFFBBFRLL")

        // When
        val seatRow = boardingPass.seatRow()

        // Then
        assertThat(seatRow).isEqualTo(102)
    }

    @Test
    fun `seat should be between column 0 and 3 when height direction letter is L`() {
        // Given
        val boardingPass = BoardingPass("FFFBBBFLRR")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isBetween(0, 3)
    }

    @Test
    fun `seat should be between column 1 and 3 when first direction letter is L and second is R`() {
        // Given
        val boardingPass = BoardingPass("FBFBBBFLRR")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isBetween(1, 3)
    }

    @Test
    fun `seat should be between column 4 and 7 when height direction letter is R`() {
        // Given
        val boardingPass = BoardingPass("BFFBBBFRRR")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isBetween(4, 7)
    }

    @Test
    fun `seat should be column number 7 when its directions are "BFFFBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("BFFFBBFRRR")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isEqualTo(7)
    }

    @Test
    fun `seat should be column number 7 when its directions are "FFFBBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("FFFBBBFRRR")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isEqualTo(7)
    }

    @Test
    fun `seat should be column number 4 when its directions are "BBFFBBFRLL"`() {
        // Given
        val boardingPass = BoardingPass("BBFFBBFRLL")

        // When
        val seatColumn = boardingPass.seatColumn()

        // Then
        assertThat(seatColumn).isEqualTo(4)
    }

    @Test
    fun `seat should be seat id 567 when its directions are "BFFFBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("BFFFBBFRRR")

        // When
        val seatId = boardingPass.seatId()

        // Then
        assertThat(seatId).isEqualTo(567)
    }

    @Test
    fun `seat should be seat id 119 when its directions are "FFFBBBFRRR"`() {
        // Given
        val boardingPass = BoardingPass("FFFBBBFRRR")

        // When
        val seatId = boardingPass.seatId()

        // Then
        assertThat(seatId).isEqualTo(119)
    }

    @Test
    fun `seat should be seat id 820 when its directions are "BBFFBBFRLL"`() {
        // Given
        val boardingPass = BoardingPass("BBFFBBFRLL")

        // When
        val seatId = boardingPass.seatId()

        // Then
        assertThat(seatId).isEqualTo(820)
    }


    @Test
    fun `Day 5 - Part 1`() {
        // Given
        val boardingPasses = REAL_BOARDING_PASSES.map { BoardingPass(it)}

        // When
        val boardingPassesSeatIds = boardingPasses.map {it.seatId()}

        // Then
        assertThat(boardingPassesSeatIds.maxOrNull()).isEqualTo(888)
    }
}