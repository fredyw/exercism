class Clock(private var hours: Int, private var minutes: Int) {
    init {
        normalize()
    }

    fun subtract(minutes: Int) {
        this.minutes -= minutes
        normalize()
    }

    fun add(minutes: Int) {
        this.minutes += minutes
        normalize()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Clock

        if (hours != other.hours) return false
        if (minutes != other.minutes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hours
        result = 31 * result + minutes
        return result
    }

    override fun toString(): String {
        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
    }

    private fun normalize() {
        val m = minutes % 60
        val h = (hours + (minutes / 60) + if (m < 0) -1 else 0) % 24
        hours = if (h < 0) 24 + h else h
        minutes = if (m < 0) 60 + m else m
    }
}
