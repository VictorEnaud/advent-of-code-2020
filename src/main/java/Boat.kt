import kotlin.math.abs

class Boat(textInstructions: List<String>) {
    private val instructions = textInstructions.map { Instruction(it) }

    private var orientation = Orientation()
    private var position = Position()

    fun move() {
        instructions.forEach {
            if (it.isDirection()) {
                position.move(it.command, it.value)
            } else if (it.isOrientation()) {
                orientation.rotate(it.command, it.value)
            } else {
                position.move(orientation.direction(), it.value)
            }
        }
    }

    fun manhattanDistance(): Int {
        return position.manhattanDistance()
    }

    inner class Instruction(textInstruction: String) {
        val command = textInstruction.take(1)
        val value = textInstruction.drop(1).toInt()

        fun isDirection(): Boolean {
            return command in listOf("W", "E", "S", "N")
        }

        fun isOrientation(): Boolean {
            return command in listOf("L", "R")
        }
    }

    inner class Orientation() {
        private var MAX_DEGREES = 360
        private var orientation = 90

        fun direction(): String {
            return when (orientation) {
                0 -> "N"
                90 -> "E"
                180 -> "S"
                270 -> "W"
                else -> "other"
            }
        }

        fun rotate(direction: String, rotation: Int) {
            when (direction) {
                "L" -> orientation = (orientation - rotation + MAX_DEGREES) % MAX_DEGREES
                "R" -> orientation = (orientation + rotation) % MAX_DEGREES
            }
        }
    }

    inner class Position() {
        private var north = 0
        private var south = 0
        private var east = 0
        private var west = 0

        fun move(direction: String, value: Int) {
            when (direction) {
                "N" -> north += value
                "S" -> south += value
                "E" -> east += value
                "W" -> west += value
            }
        }

        fun manhattanDistance(): Int {
            return abs(north - south) + abs(east - west)
        }
    }
}