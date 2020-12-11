class LuggageProcessing(textLuggageRules: List<String>) {

    private val luggageRules = textLuggageRules.map { LuggageRule(it) }
    private val luggageRulesTree = luggageRules.map { parentBag ->
        LuggageRuleNode(parentBag.color, 1, buildChildNodes(parentBag))
    }

    private fun buildChildNodes(parentBag: LuggageRule): List<LuggageRuleNode> =
        parentBag.containedColors.map { containedColor ->
            val containedColorRule = luggageRules.find { luggageRule -> luggageRule.color == containedColor.color }
            if (containedColorRule != null) {
                LuggageRuleNode(containedColor.color, containedColor.number, buildChildNodes(containedColorRule))
            } else {
                LuggageRuleNode(containedColor.color, containedColor.number, emptyList())
            }
        }


    fun numberOfBagsContainingColor(bagColor: String): Int {
        return luggageRulesTree.filter { it.containColor(bagColor) }.count()
    }

    fun numberOfBagsContainedBy(bagColor: String): Int {
        return luggageRulesTree.find { it.isColor(bagColor) }?.numberOfContainedBags() ?: 0
    }

    inner class LuggageRule(textLuggageRule: String) {
        private val bagSuffixRegex = " bag.*".toRegex()
        val color = textLuggageRule.split(" contain ")[0].replace(bagSuffixRegex, "")
        val containedColors = textLuggageRule.split(" contain ")[1]
            .split(", ")
            .filter { !it.contains("no other bags") }
            .map { ContainedColor(it.replace(bagSuffixRegex, "").drop(2), it.take(1)) }

        inner class ContainedColor(val color: String, textNumber: String) {
            val number = textNumber.toInt()
        }
    }

    inner class LuggageRuleNode(
        private val color: String,
        private val number: Int,
        private val containedLuggages: List<LuggageRuleNode?>
    ) {
        fun numberOfContainedBags(): Int {
            return if (containedLuggages.isEmpty()) {
                0
            } else {
                containedLuggages.fold(0) { numberOfContainedBags, containedLuggage ->
                    numberOfContainedBags + containedLuggage!!.number + containedLuggage.number * containedLuggage.numberOfContainedBags()
                }
            }
        }

        fun containColor(bagColor: String): Boolean {
            return containedLuggages.any { it?.isColor(bagColor) ?: false || it?.containColor(bagColor) ?: false }
        }

        fun isColor(bagColor: String): Boolean {
            return color == bagColor
        }
    }
}