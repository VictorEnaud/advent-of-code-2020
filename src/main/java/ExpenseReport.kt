class ExpenseReport(private val expenses: ArrayList<Int>) {
    fun get2020Multiplication(): Int {
        val expensesToMultiply = expenses.filter { expense -> expenses.map { otherExpense->expense+otherExpense }.contains(2020)}


        return expensesToMultiply[0] * expensesToMultiply[1]
    }
}