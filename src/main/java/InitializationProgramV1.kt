import kotlin.math.pow

class InitializationProgramV1 {
    private var bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    private val memory = emptyMap<Int, Bit>().toMutableMap()

    fun saveInMemoryAtAddress(address: Int, value: Long) {
        memory[address] = Bit(bitmask, value)
    }

    fun sumOfMemoryValues(): Long {
        return memory.values.fold(0) { acc, it -> acc + it.toDecimal() }
    }

    fun updateBitmask(newBitmask: String) {
        bitmask = newBitmask
    }

    class Bit(bitmask: String, decimal: Long) {
        private val value: String

        init {
            val binary = StringBuilder(toBinary(decimal).padStart(36, '0'))
            bitmask.forEachIndexed { index, it ->
                if (it != 'X') {
                    binary[index] = it
                }
            }
            value = binary.toString()
        }

        fun toDecimal(): Long {
            var sum = 0L
            value.reversed().forEachIndexed { k, v ->
                sum += v.toString().toLong() * 2.toDouble().pow(k).toLong()
            }
            return sum
        }

        private fun toBinary(decimalNumber: Long, binaryString: String = ""): String {
            while (decimalNumber > 0) {
                val temp = "${binaryString}${decimalNumber % 2}"
                return toBinary(decimalNumber / 2, temp)
            }
            return binaryString.reversed()
        }
    }
}
