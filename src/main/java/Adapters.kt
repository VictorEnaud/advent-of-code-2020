class Adapters(textAdapters: List<String>) {
    val adapters = textAdapters.map { textAdapter -> textAdapter.toInt() }.sorted()

    fun oneAndThreeJoltsDifferenceMultiplication(): Int {
        var oneJoltDifferences = 1
        var threeJoltDifferences = 1
        adapters.reduce { previousAdapter, currentAdapter ->
            if (currentAdapter - previousAdapter == 1) {
                oneJoltDifferences++
            } else if (currentAdapter-previousAdapter==3) {
                threeJoltDifferences++
            }
            return@reduce currentAdapter
        }

        return oneJoltDifferences * threeJoltDifferences
    }
}