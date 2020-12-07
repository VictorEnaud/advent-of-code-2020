
class Passports(textPassports: List<String>) {
    private val passports: List<Passport>

    init {
        val normalizedTextPassports = textPassports.fold(mutableListOf("")) { accumulator, textPassport ->
            if (textPassport == "") {
                accumulator.add(textPassport)
                return@fold accumulator
            } else {
                accumulator[accumulator.lastIndex] = accumulator.last().plus(" $textPassport")
                return@fold accumulator
            }
        }

        passports = normalizedTextPassports.map { Passport(it) }
    }


    fun validPassports(): Int {
        return passports.filter { it.isValid() }.size
    }

    class Passport(private val textPassport: String) {
        private val BIRTH_YEAR_CODE = "byr"
        private val ISSUE_YEAR_CODE = "iyr"
        private val EXPIRATION_YEAR_CODE = "eyr"
        private val HEIGHT_CODE = "hgt"
        private val HAIR_COLOR_CODE = "hcl"
        private val EYE_COLOR_CODE = "ecl"
        private val ID_CODE = "pid"
        private val COUNTRY_ID_CODE = "cid"

        private val birthYear: String?
        private val issueYear: String?
        private val expirationYear: String?
        private val height: String?
        private val hairColor: String?
        private val eyeColor: String?
        private val id: String?
        private val countryId: String?

        init {
            val passportFields = textPassport.split(" ")
                .associate { Pair(it.substringBefore(":"), it.substringAfter(":")) }
            birthYear = passportFields[BIRTH_YEAR_CODE]
            issueYear = passportFields[ISSUE_YEAR_CODE]
            expirationYear = passportFields[EXPIRATION_YEAR_CODE]
            height = passportFields[HEIGHT_CODE]
            hairColor = passportFields[HAIR_COLOR_CODE]
            eyeColor = passportFields[EYE_COLOR_CODE]
            id = passportFields[ID_CODE]
            countryId = passportFields[COUNTRY_ID_CODE]
        }


        fun isValid(): Boolean {
            return !(birthYear.isNullOrEmpty() ||
                    issueYear.isNullOrEmpty() ||
                    expirationYear.isNullOrEmpty() ||
                    height.isNullOrEmpty() ||
                    hairColor.isNullOrEmpty() ||
                    eyeColor.isNullOrEmpty() ||
                    id.isNullOrEmpty())
        }
    }
}