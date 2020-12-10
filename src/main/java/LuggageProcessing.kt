class LuggageProcessing(textLuggageRules: List<String>) {

    private val luggageRules = textLuggageRules.map { LuggageRule(it) }
    private val luggageRulesTree = luggageRules.map { parentBag ->
        LuggageRuleNode(parentBag.color, buildChildNodes(parentBag))
    }

    private fun buildChildNodes(parentBag: LuggageRule):List<LuggageRuleNode> =
        parentBag.containedColors.map { containedColor ->
            val containedColorRule = luggageRules.find { luggageRule -> luggageRule.color == containedColor }
            if (containedColorRule != null) {
                LuggageRuleNode(containedColorRule.color, buildChildNodes(containedColorRule))
            } else {
                LuggageRuleNode(containedColor, emptyList())
            }
        }


    fun numberOfBagsContainingColor(bagColor: String): Int {
        return luggageRulesTree.filter { it.containColor(bagColor) }.count()
    }

    inner class LuggageRule(textLuggageRule: String) {
        private val bagSuffixRegex = " bag.*".toRegex()
        val color = textLuggageRule.split(" contain ")[0].replace(bagSuffixRegex, "")
        val containedColors = textLuggageRule.split(" contain ")[1]
            .split(", ")
            .map {
                it.replace(bagSuffixRegex, "")
                    .drop(2)
            }
    }

    inner class LuggageRuleNode(val color: String, val containedLuggages: List<LuggageRuleNode?>) {
        fun containColor(bagColor: String): Boolean {
            return containedLuggages.any { it?.isColor(bagColor) ?: false || it?.containColor(bagColor) ?: false }
        }

        private fun isColor(bagColor: String): Boolean {
            return color == bagColor
        }
    }
}