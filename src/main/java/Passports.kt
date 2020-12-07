import java.util.Arrays.asList

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

        private val birthYear: BirthYear
        private val issueYear: IssueYear
        private val expirationYear: ExpirationYear
        private val height: Height
        private val hairColor: HairColor
        private val eyeColor: EyeColor
        private val id: Id
        private val countryId: String?

        init {
            val passportFields = textPassport.split(" ")
                .associate { Pair(it.substringBefore(":"), it.substringAfter(":")) }
            birthYear = BirthYear(passportFields[BIRTH_YEAR_CODE])
            issueYear = IssueYear(passportFields[ISSUE_YEAR_CODE])
            expirationYear = ExpirationYear(passportFields[EXPIRATION_YEAR_CODE])
            height = Height(passportFields[HEIGHT_CODE])
            hairColor = HairColor(passportFields[HAIR_COLOR_CODE])
            eyeColor = EyeColor(passportFields[EYE_COLOR_CODE])
            id = Id(passportFields[ID_CODE])
            countryId = passportFields[COUNTRY_ID_CODE]
        }

        fun isValid(): Boolean {
            return (birthYear.isValid() &&
                    issueYear.isValid() &&
                    expirationYear.isValid() &&
                    height.isValid() &&
                    hairColor.isValid() &&
                    eyeColor.isValid() &&
                    id.isValid())
        }


        class BirthYear(textYear: String?) {
            private val year: Int = textYear?.toInt() ?: 0

            fun isValid(): Boolean {
                return year in 1920..2002
            }
        }

        class IssueYear(textYear: String?) {
            private val year: Int = textYear?.toInt() ?: 0

            fun isValid(): Boolean {
                return year in 2010..2020
            }
        }

        class ExpirationYear(textYear: String?) {
            private val year: Int = textYear?.toInt() ?: 0

            fun isValid(): Boolean {
                return year in 2020..2030
            }
        }

        class Height(textHeight: String?) {
            private val height = textHeight?.dropLast(2)?.toIntOrNull() ?: 0
            private val unit = textHeight?.takeLast(2) ?: ""

            fun isValid(): Boolean {
                if (unit == "in") {
                    return height in 59..76
                } else if (unit == "cm") {
                    return height in 150..193
                }
                return false
            }
        }

        class HairColor(private val color: String?) {
            private val colorRegex = "#[0-9a-f]{6}".toRegex()

            fun isValid(): Boolean {
                return color?.let { colorRegex.matches(it) } ?: false
            }
        }

        class EyeColor(private val color: String?) {
            private val possibleColors = asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            fun isValid(): Boolean {
                return possibleColors.contains(color)
            }
        }

        class Id(private val id: String?) {
            private val idRegex = "[0-9]{9}".toRegex()

            fun isValid(): Boolean {
                return id?.let { idRegex.matches(it) } ?: false
            }
        }
    }
}