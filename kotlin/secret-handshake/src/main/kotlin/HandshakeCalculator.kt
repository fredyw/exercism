object HandshakeCalculator {
    fun calculateHandshake(number: Int): List<Signal> {
        return mutableListOf<Signal>().apply {
            if (number and 0b1 == 0b1) {
                add(Signal.WINK)
            }
            if (number and 0b10 == 0b10) {
                add(Signal.DOUBLE_BLINK)
            }
            if (number and 0b100 == 0b100) {
                add(Signal.CLOSE_YOUR_EYES)
            }
            if (number and 0b1000 == 0b1000) {
                add(Signal.JUMP)
            }
            if (number and 0b10000 == 0b10000) {
                reverse()
            }
        }
    }
}
