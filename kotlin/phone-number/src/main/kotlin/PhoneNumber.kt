class PhoneNumber(private val phoneNumber: String) {
    val number: String? =
        phoneNumber.filter { it.isDigit() }.let {
            require(it.matches("1?[2-9]\\d{2}[2-9]\\d{6}".toRegex()))
            if (it.length == 11) {
                it.substring(1, it.length)
            } else {
                it
            }
        }
}
