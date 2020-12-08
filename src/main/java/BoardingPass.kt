class BoardingPass(private val directions: String) {
    private val FRONT = 'F'
    private val LEFT = 'L'
    private val LAST_ROW = 127
    private val LAST_COLUMN = 7

    fun seatRow(): Int {
        return directions.take(7)
            .fold(intArrayOf(0, LAST_ROW)) { rowInterval, direction ->
                val intervalMiddle = (rowInterval[1]-rowInterval[0])/2 + rowInterval[0]
                if (direction == FRONT) {
                    return@fold intArrayOf(rowInterval[0], intervalMiddle)
                } else {
                    return@fold intArrayOf(intervalMiddle + 1, rowInterval[1])
                }
            }
            .first()
    }

    fun seatColumn(): Int {
        return directions.takeLast(3)
            .fold(intArrayOf(0, LAST_COLUMN)) { rowInterval, direction ->
                val intervalMiddle = (rowInterval[1]-rowInterval[0])/2 + rowInterval[0]
                if (direction == LEFT) {
                    return@fold intArrayOf(rowInterval[0], intervalMiddle)
                } else {
                    return@fold intArrayOf(intervalMiddle + 1, rowInterval[1])
                }
            }
            .first()
    }

    fun seatId(): Int {
        return seatRow() * 8 + seatColumn()
    }
}