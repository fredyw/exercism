object RunLengthEncoding {
    fun encode(input: String): String {
        return input.replace("(.)\\1+".toRegex()) {
            if (it.value.length == 1) {
                it.groupValues[1]
            } else {
                "${it.value.length}${it.groupValues[1]}"
            }
        }
    }

    fun decode(input: String): String {
        return input.replace("(\\d+)(.)".toRegex()) {
            it.groupValues[2].repeat(it.groupValues[1].toInt())
        }
    }
}
