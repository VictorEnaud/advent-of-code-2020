import kotlin.math.abs

class Boat(textInstructions: List<String>) {
    private val instructions = textInstructions.map { Instruction(it) }

    private var position = Position()
    private var waypoint = Waypoint()

    fun move() {
        instructions.forEach {
            if (it.isDirection()) {
                waypoint.move(it.command, it.value)
            } else if (it.isOrientation()) {
                if (it.command == "R") {
                    waypoint.coordinates.rotateClockwise(it.value)
                } else {
                    waypoint.coordinates.rotateCounterClockwise(it.value)
                }
            } else {
                position.move(waypoint, it.value)
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

    inner class Position() {
        private var coordinates = Coordinates(0, 0, 0, 0)
        fun move(waypoint: Waypoint, value: Int) {
            coordinates += waypoint.coordinates * value
        }

        fun manhattanDistance(): Int {
            return coordinates.manhattanDistance()
        }

    }

    inner class Waypoint() {
        internal var coordinates = Coordinates(1, 0, 10, 0)
        fun move(direction: String, value: Int) {
            when (direction) {
                "N" -> coordinates.north += value
                "S" -> coordinates.south += value
                "E" -> coordinates.east += value
                "W" -> coordinates.west += value
            }
        }
    }

    inner class Coordinates(
        internal var north: Int,
        internal var south: Int,
        internal var east: Int,
        internal var west: Int
    ) {
        fun manhattanDistance(): Int {
            return abs(north - south) + abs(east - west)
        }

        fun rotateClockwise(degrees: Int) {
            val quarter = degrees / 90
            for (rotation in 1..quarter) {
                val oldSouth = south
                south = east
                east = north
                north = west
                west = oldSouth
            }
        }

        fun rotateCounterClockwise(degrees: Int) {
            val quarter = degrees / 90
            for (rotation in 1..quarter) {
                val oldSouth = south
                south = west
                west = north
                north = east
                east = oldSouth
            }
        }

        operator fun plus(coordinates: Coordinates): Coordinates {
            return Coordinates(
                north + coordinates.north,
                south + coordinates.south,
                east + coordinates.east,
                west + coordinates.west
            )
        }

        operator fun times(value: Int): Coordinates {
            return Coordinates(north * value, south * value, east * value, west * value)
        }
    }
}