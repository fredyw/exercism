class BankAccount {
    var balance: Long = 0
        get() {
            check(!closed)
            return field
        }

    private var closed: Boolean = false

    @Synchronized
    fun adjustBalance(amount: Long) {
        check(!closed)
        balance += amount
    }

    fun close() {
        closed = true
    }
}
