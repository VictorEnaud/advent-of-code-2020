import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

val REAL_BUS_INPUT = File("src/test/resources/DAY_13_DATA.txt").readLines()

internal class ShuttleSearchTest {
    @Test
    fun `should return bus id and waiting time`() {
        // Given
        val shuttleSearch = ShuttleSearch("2")
        val earliestDepartureTime = 1

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        assertThat(waitedBus.id).isEqualTo(2)
        assertThat(waitedBus.departureTime - earliestDepartureTime).isEqualTo(1)
    }

    @Test
    fun `buses should leave every minutes defined by its id`() {
        // Given
        val shuttleSearch = ShuttleSearch("5")
        val earliestDepartureTime = 27

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        assertThat(waitedBus.id).isEqualTo(5)
        assertThat(waitedBus.departureTime - earliestDepartureTime).isEqualTo(3)
    }

    @Test
    fun `should wait for the first bus leaving after departure time`() {
        // Given
        val shuttleSearch = ShuttleSearch("3,5")
        val earliestDepartureTime = 34

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        assertThat(waitedBus.id).isEqualTo(5)
        assertThat(waitedBus.departureTime - earliestDepartureTime).isEqualTo(1)
    }

    @Test
    fun `should ignore non working buses`() {
        // Given
        val shuttleSearch = ShuttleSearch("3,x,5")
        val earliestDepartureTime = 61

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        assertThat(waitedBus.id).isEqualTo(3)
        assertThat(waitedBus.departureTime - earliestDepartureTime).isEqualTo(2)
    }

    @Test
    fun example() {
        // Given
        val shuttleSearch = ShuttleSearch("7,13,x,x,59,x,31,19")
        val earliestDepartureTime = 939

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        assertThat(waitedBus.id).isEqualTo(59)
        assertThat(waitedBus.departureTime - earliestDepartureTime).isEqualTo(5)
    }

    @Test
    fun `Day 13 - Part 1`() {
        // Given
        val shuttleSearch = ShuttleSearch(REAL_BUS_INPUT[1])
        val earliestDepartureTime = REAL_BUS_INPUT[0].toInt()

        // When
        val waitedBus = shuttleSearch.findFirstBusAfter(earliestDepartureTime)

        // Then
        val waitingTime = waitedBus.departureTime - earliestDepartureTime
        assertThat(waitedBus.id * waitingTime).isEqualTo(4722)
    }
}