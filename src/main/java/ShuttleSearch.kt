class ShuttleSearch(textWorkingBuses: String) {
    private val workingBuses = textWorkingBuses.split(",")
        .mapIndexedNotNull { index, busId -> if (busId != "x") Bus(busId.toInt(), index) else null }

    fun findFirstBusAfter(earliestDepartureTime: Int): BusDeparture {
        val workingBusesFirstDepartureTimeSorted = workingBuses.map { it.firstDepartureAfter(earliestDepartureTime) }
            .sortedBy { it.departureTime }
        return workingBusesFirstDepartureTimeSorted.first()
    }

    fun findFirstTimestampWhereBusesLeaveInOrder(): Long {
        val products = workingBuses.map { currentBus ->
                val minimalProduct = workingBuses.filterNot{it.id == currentBus.id}
                    .fold(1L) { product, bus -> product * bus.id }
                var i = 0
                var product = minimalProduct
                while (product % currentBus.id != 1L) {
                    i++
                    product = i * minimalProduct
                }
                Pair(product, currentBus)
        }

        val someCorrectNumber = products.fold(0L) { acc, pair -> acc + pair.first * (pair.second.id- pair.second.position)}
        val interval = workingBuses.fold(1L) { interval, bus -> interval * bus.id }

        val minimumCorrectNumber = someCorrectNumber % interval

        workingBuses.map{minimumCorrectNumber%it.id}
        return minimumCorrectNumber
    }

    inner class Bus(val id: Int, val position: Int) {
        fun firstDepartureAfter(earliestDepartureTime: Int): BusDeparture {
            val nextRotationNumber = (earliestDepartureTime + id - 1) / id
            return BusDeparture(id, nextRotationNumber * id)
        }
    }

    inner class BusDeparture(val id: Int, val departureTime: Int)
}