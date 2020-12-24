class SeatsLayout(textLayout: String) {
    private var seatsLayout: List<List<Position>>
    private val EMPTY_SEAT_SYMBOL = 'L'
    private val OCCUPIED_SEAT_SYMBOL = '#'
    private val ALLOWED_ADJACENT_OCCUPIED_SEATS_TO_OCCUPY = 0
    private val MAX_ADJACENT_OCCUPIED_SEATS = 4

    init {
        seatsLayout = textLayout.split("\n")
            .map { layoutLine ->
                layoutLine.map { textPosition ->
                    when (textPosition) {
                        EMPTY_SEAT_SYMBOL -> Seat(false)
                        OCCUPIED_SEAT_SYMBOL -> Seat(true)
                        else -> Floor()
                    }
                }
            }
    }

    fun fill() {
        seatsLayout = seatsLayout.mapIndexed { currentLineIndex, line ->
            line.mapIndexed { currentColumnIndex: Int, position: Position ->
                if (position is Seat) {
                    val adjacentOccupiedSeats = adjacentOccupiedSeats(currentLineIndex, currentColumnIndex)
                    if (!position.isOccupied() && adjacentOccupiedSeats == ALLOWED_ADJACENT_OCCUPIED_SEATS_TO_OCCUPY) Seat(
                        true
                    )
                    else if (position.isOccupied() && adjacentOccupiedSeats > MAX_ADJACENT_OCCUPIED_SEATS) Seat(false)
                    else position
                } else position
            }
        }
    }

    private fun adjacentOccupiedSeats(currentLineIndex: Int, currentColumnIndex: Int): Int {
        var adjacentOccupiedSeats = 0
        for (lineIndex in currentLineIndex - 1..currentLineIndex + 1) {
            for (columnIndex in currentColumnIndex - 1..currentColumnIndex + 1) {
                if (seatsLayout.elementAtOrNull(lineIndex)?.elementAtOrNull(columnIndex)?.isOccupied() == true) {
                    adjacentOccupiedSeats++
                }
            }
        }
        return adjacentOccupiedSeats
    }

    fun occupiedSeats(): Int {
        return seatsLayout.flatten()
            .fold(0) { occupiedSeats, position ->
                if (position.isOccupied()) occupiedSeats + 1 else occupiedSeats
            }
    }

    interface Position {
        fun isOccupied(): Boolean
    }

    inner class Floor : Position {
        override fun isOccupied(): Boolean = false
    }

    inner class Seat(private var isOccupied: Boolean) : Position {
        override fun isOccupied(): Boolean = isOccupied
        fun occupySeat() {
            isOccupied = true
        }
    }
}