class ExpenseReport(private val expenses: ArrayList<Int>) {
    fun getTwo2020Multiplication(): Int {
        val expensesToMultiply = expenses.filter { expense ->
            expenses.map { otherExpense ->
                expense + otherExpense
            }.contains(2020)
        }

        return expensesToMultiply[0] * expensesToMultiply[1]
    }

    fun getThree2020Multiplication(): Int {
        val expensesToMultiply = expenses.filter { expense -> expenses.flatMap { secondExpense ->
            expenses.map { thirdExpense ->
                expense + secondExpense + thirdExpense
            }
        }.contains(2020) }

        return expensesToMultiply[0] * expensesToMultiply[1] * expensesToMultiply[2]
    }
}