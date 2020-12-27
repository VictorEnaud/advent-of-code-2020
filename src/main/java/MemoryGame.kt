class MemoryGame(private val startingNumbers: List<Int>) {
    private val spokenNumbers = startingNumbers.toMutableList()
    private var currentTurn = 0

    fun nextSpokenNumber(): Int {
        val nextSpokenNumber: Int
        if (currentTurn < startingNumbers.size) {
            nextSpokenNumber = startingNumbers[currentTurn]
        } else {
            val previousTurn = currentTurn - 1
            val previousSpokenNumber = spokenNumbers.last()
            val lastTurnSpoken = spokenNumbers.dropLast(1)
                .lastIndexOf(previousSpokenNumber)
            nextSpokenNumber = if (lastTurnSpoken != -1) {
                previousTurn - lastTurnSpoken
            } else {
                0
            }
            spokenNumbers.add(nextSpokenNumber)
        }
        currentTurn++
        return nextSpokenNumber
    }
}