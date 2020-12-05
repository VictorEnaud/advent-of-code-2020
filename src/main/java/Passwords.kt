import java.lang.Integer.parseInt

class Passwords(private val textPasswordsAndValidations: List<String>) {
    private val passwords = textPasswordsAndValidations.map { Password(it) }

    fun validPasswords(): Int {
        return passwords.filter { it.isValid() }.size
    }

    class Password(private val textPasswordAndValidation: String) {
        private val firstRequestedPosition = parseInt(textPasswordAndValidation.split(" ")[0].split("-")[0])
        private val secondRequestedPosition = parseInt(textPasswordAndValidation.split(" ")[0].split("-")[1])
        private val mandatoryLetter = textPasswordAndValidation.split(" ")[1][0]
        private val password = textPasswordAndValidation.split(" ")[2]

        fun isValid(): Boolean {
            val firstMandatoryPositionLetter = password[firstRequestedPosition - 1]
            val secondMandatoryPositionLetter = password.elementAtOrNull(secondRequestedPosition - 1)
            return (firstMandatoryPositionLetter == mandatoryLetter) xor (secondMandatoryPositionLetter == mandatoryLetter)
        }

    }
}