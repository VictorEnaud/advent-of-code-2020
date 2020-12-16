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