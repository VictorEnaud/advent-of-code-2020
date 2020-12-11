class GameConsole(textInstructions: List<String>) {
    private val instructions = textInstructions.map{Instruction(it)}
    private var currentInstructionIndex = 0
    private var instructionsRunt = mutableListOf<Int>()
    private var accumulator =0

    fun run():Int {
        while(continueRun()) {
            instructionsRunt.add(currentInstructionIndex)
            runInstruction(instructions[currentInstructionIndex])
        }
        return accumulator
    }

    private fun continueRun(): Boolean {
        return currentInstructionIndex < instructions.size && !instructionsRunt.contains(currentInstructionIndex)
    }

    private fun runInstruction(instruction: Instruction) {
        when {
            instruction.isType("acc") -> {
                accumulator+= instruction.value
                currentInstructionIndex++
            }
            instruction.isType("nop") -> {
                currentInstructionIndex++
            }
            instruction.isType("jmp") -> {
                currentInstructionIndex+=instruction.value
            }
        }
    }


    inner class Instruction(textInstruction: String) {
        private val type = textInstruction.split(" ")[0]
        val value = textInstruction.split((" "))[1].toInt()

        fun isType(type:String):Boolean {
            return this.type == type
        }
    }
}