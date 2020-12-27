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

class InitializationProgramV2 {
    private var bitmask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    private val memory = emptyMap<Long, Long>().toMutableMap()

    fun saveInMemoryAtAddress(address: Int, value: Long) {
        Bit(address).applyBitmask(bitmask).forEach {
            memory[it] = value
        }
    }

    fun sumOfMemoryValues(): Long {
        return memory.values.sum()
    }

    fun updateBitmask(newBitmask: String) {
        bitmask = newBitmask
    }

    class Bit {
        private val value: String

        constructor(decimal: Int) {
            value = toBinary(decimal).padStart(36, '0')
        }

        constructor(binaryString: String) {
            value = binaryString
        }

        private fun toDecimal(): Long {
            var sum = 0L
            value.reversed().forEachIndexed { k, v ->
                sum += v.toString().toLong() * 2.toDouble().pow(k).toLong()
            }
            return sum
        }

        private fun toBinary(decimalNumber: Int, binaryString: String = ""): String {
            while (decimalNumber > 0) {
                val temp = "${binaryString}${decimalNumber % 2}"
                return toBinary(decimalNumber / 2, temp)
            }
            return binaryString.reversed()
        }

        fun applyBitmask(bitmask: String): List<Long> {
            var binaries = listOf(value)
            bitmask.forEachIndexed { index, it ->
                run {
                    when (it) {
                        '1' -> {
                            binaries = binaries.map { binary ->
                                val binaryStringBuilder = StringBuilder(binary)
                                binaryStringBuilder[index] = '1'
                                "$binaryStringBuilder"
                            }
                        }
                        'X' -> {
                            var tempBinaries = emptyList<String>()
                            binaries.forEach { binary ->
                                val binaryStringBuilder = StringBuilder(binary)
                                binaryStringBuilder[index] = '1'
                                tempBinaries = tempBinaries.plus("$binaryStringBuilder")
                                binaryStringBuilder[index] = '0'
                                tempBinaries = tempBinaries.plus("$binaryStringBuilder")
                            }
                            binaries = tempBinaries
                        }
                    }
                }
            }
            return binaries.map { Bit(it).toDecimal() }
        }
    }
}