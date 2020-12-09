class CustomForm(private val groupCheckedConditions: List<String>) {
    fun numberOfCheckedConditionsByGroup(): Int {
        return groupCheckedConditions.reduce { commonCheckedConditions, checkedConditions ->
            keepCommonCheckedConditions(commonCheckedConditions, checkedConditions)
        }.count()
    }

    private fun keepCommonCheckedConditions(
        firstCheckedConditions: String,
        secondCheckedConditions: String
    ): String {
        return firstCheckedConditions.toList()
            .intersect(secondCheckedConditions.toList())
            .joinToString("")
    }
}