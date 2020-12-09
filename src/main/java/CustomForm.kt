class CustomForm(private val groupCheckedConditions: List<String>) {
    fun numberOfCheckedConditionsByGroup(): Int {
        return groupCheckedConditions.reduce{concatenation, checkedConditions ->  concatenation.plus(checkedConditions)}
            .toList()
            .distinct()
            .count()
    }
}