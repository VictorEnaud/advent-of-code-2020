class Map(val rightMovement: Int, val downMovement: Int, private val mapLines: List<String>) {
    private val position = Position()
    private val treeSymbol = '#'

    fun numberOfTreesInTobogganPath(): Int {
        var numberOfTreesEncountered = 0
        while (position.currentLine < mapLines.size - 1) {
            position.move(rightMovement, downMovement)
            if (isTreeOnPosition()) numberOfTreesEncountered++
        }
        return numberOfTreesEncountered
    }

    private fun isTreeOnPosition(): Boolean {
        val numberOfColumns = mapLines[0].count()
        return mapLines[position.currentLine][position.currentColumn % numberOfColumns] == treeSymbol
    }

    internal class Position() {
        internal var currentLine = 0
        internal var currentColumn = 0

        fun move(rightMovement: Int, downMovement: Int) {
            currentLine+= downMovement
            currentColumn += rightMovement
        }
    }
}