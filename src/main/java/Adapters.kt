import kotlin.math.pow

class Adapters(textAdapters: List<String>) {
    private val adapters: List<Int>

    init {
        val convertedAdapters = textAdapters.map { textAdapter -> textAdapter.toInt() }
        adapters = convertedAdapters.plus(0)
            .plus(convertedAdapters.maxOrNull()!! + 3)
            .sorted()
    }

    fun oneAndThreeJoltsDifferenceMultiplication(): Int {
        var oneJoltDifferences = 0
        var threeJoltDifferences = 0
        adapters.reduce { previousAdapter, currentAdapter ->
            if (currentAdapter - previousAdapter == 1) {
                oneJoltDifferences++
            } else if (currentAdapter - previousAdapter == 3) {
                threeJoltDifferences++
            }
            return@reduce currentAdapter
        }

        return oneJoltDifferences * threeJoltDifferences
    }

    fun possibleCombinations(): Long {
        var possibleCombinations = 1L

        val adaptersWithoutDevice = adapters.dropLast(1)
        var index = 0
        while (index in 0..adaptersWithoutDevice.size) {
            val adapter = adaptersWithoutDevice[index]
            val possibleAdapters = adapters.filter { it in adapter + 1..adapter + 3 }
            var additionalCombinations = 2.toDouble().pow(possibleAdapters.count() - 1)
            index=adaptersWithoutDevice.indexOf(possibleAdapters.last())
            if (possibleAdapters.count() == 3) {
                var i = 4
                while (adapters.contains(adapter+i)) {
                    additionalCombinations+=3
                    index=adapters.indexOf(adapter+i)
                    i++
                }
            }
            possibleCombinations *= additionalCombinations.toLong()
        }

        return possibleCombinations
    }
}