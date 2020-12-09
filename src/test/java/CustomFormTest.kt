import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.util.Arrays.asList

val REAL_CUSTOM_FORMS = File("src/test/resources/DAY_6_DATA.txt").readText()
    .split("\n\n")

internal class CustomFormTest {
    @Test
    fun `numberOfCheckedConditionsByGroup should return number of conditions checked by 1 person`() {
        // Given
        val customForm = CustomForm(listOf("abd"))

        // When
        val numberOfCheckedConditionsByGroup = customForm.numberOfCheckedConditionsByGroup()

        // Then
        assertThat(numberOfCheckedConditionsByGroup).isEqualTo(3)
    }

    @Test
    fun `numberOfCheckedConditionsByGroup should not count a condition if not checked by all group`() {
        // Given
        val customForm = CustomForm(listOf("abd", "efg"))

        // When
        val numberOfCheckedConditionsByGroup = customForm.numberOfCheckedConditionsByGroup()

        // Then
        assertThat(numberOfCheckedConditionsByGroup).isEqualTo(0)
    }

    @Test
    fun `numberOfCheckedConditionsByGroup should count condition checked by all group`() {
        // Given
        val customForm = CustomForm(listOf("abd", "bdg"))

        // When
        val numberOfCheckedConditionsByGroup = customForm.numberOfCheckedConditionsByGroup()

        // Then
        assertThat(numberOfCheckedConditionsByGroup).isEqualTo(2)
    }

    @Test
    fun `Day 6`() {
        // Given
        val customForms = REAL_CUSTOM_FORMS.map{CustomForm(it.split("\n"))}

        // When
        val numberOfCheckedConditionsByGroups = customForms.map{it.numberOfCheckedConditionsByGroup()}

        // Then
        assertThat(numberOfCheckedConditionsByGroups.sum()).isEqualTo(2971)
    }
}