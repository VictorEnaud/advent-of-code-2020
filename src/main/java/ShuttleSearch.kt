class ShuttleSearch(textWorkingBuses: String) {
    private val workingBuses = textWorkingBuses.split(",")
        .filterNot { it == "x" }
        .map { Bus(it.toInt()) }

    fun findFirstBusAfter(earliestDepartureTime: Int): BusDeparture {
        val workingBusesFirstDepartureTimeSorted = workingBuses.map { it.firstDepartureAfter(earliestDepartureTime) }
            .sortedBy { it.departureTime }
        return workingBusesFirstDepartureTimeSorted.first()
    }

    inner class Bus(private val id: Int) {
        fun firstDepartureAfter(earliestDepartureTime: Int): BusDeparture {
            val nextRotationNumber = (earliestDepartureTime + id - 1) / id
            return BusDeparture(id, nextRotationNumber * id)
        }
    }

    inner class BusDeparture(val id: Int, val departureTime: Int)
}