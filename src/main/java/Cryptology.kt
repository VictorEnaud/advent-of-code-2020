class Cryptology(textValues: List<String>) {
    private val values = textValues.map{it.toLong()}

    fun firstInvalidNumber(previousNumbersToConsider:Int): Long? {
        for (index in previousNumbersToConsider until values.size) {
            if (!isValid(previousNumbersToConsider, index)) {
                return values[index]
            }
        }
        return null
    }

    fun extremesOfSumRangeForNumber(invalidNumber: Long): Pair<Long?, Long?> {
        var intervalStartIndex = 0
        var sumInterval = emptyList<Long>()

        while(sumInterval.sum() != invalidNumber) {
            var intervalSum = 0L
            val valuesToConsider = values.drop(intervalStartIndex)
            sumInterval = valuesToConsider.takeWhile { value ->
                intervalSum += value
                return@takeWhile intervalSum <= invalidNumber
            }
            intervalStartIndex++
        }

        return Pair(sumInterval.minOrNull(), sumInterval.maxOrNull())
    }

    private fun isValid(previousNumbersToConsider: Int, currentIndex: Int): Boolean {
        val valuesToConsider = values.slice(currentIndex-previousNumbersToConsider until currentIndex)
        var isValid = false
        valuesToConsider.forEachIndexed { index, firstValue ->
            valuesToConsider.drop(index + 1)
                .forEach { secondValue ->
                    if (firstValue + secondValue == values[currentIndex]) isValid = true
                }
        }
        return isValid
    }
}