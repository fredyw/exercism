class BowlingGame {
    enum class ScoreType {
        OPEN_FRAME,
        SPARE,
        STRIKE
    }

    data class Frame(
        val throws: MutableList<Int> = mutableListOf(),
        var scoreType: ScoreType? = null
    )

    private val frames: MutableList<Frame> = mutableListOf()

    fun roll(pins: Int) {
        if (frames.size < 10) {
            if (frames.isEmpty()) {
                frames += Frame()
            } else {
                val lastFrame = frames.last();
                if ((lastFrame.throws.size == 1 && lastFrame.throws.sum() == 10) ||
                    lastFrame.throws.size == 2
                ) {
                    frames += Frame()
                }
            }
        }

        if (isGameDone()) {
            throw IllegalStateException("Game is done")
        }
        if (!hasEnoughPins(pins)) {
            throw IllegalStateException("Not enough pins")
        }

        val frame = frames.last()
        frame.throws += pins
        val score = frame.throws.sum()
        if (frame.scoreType == null) {
            if (frame.throws.size == 1 && score == 10) {
                frame.scoreType = ScoreType.STRIKE
            } else if (frame.throws.size == 2 && score == 10) {
                frame.scoreType = ScoreType.SPARE
            } else if (frame.throws.size == 2) {
                frame.scoreType = ScoreType.OPEN_FRAME
            }
        }
    }

    fun score(): Int {
        if (!isGameDone()) {
            throw IllegalStateException("Game is done")
        }
        val score = frames.sumOf { it.throws.sum() }
        val bonus = frames.mapIndexed { i, f ->
            when (f.scoreType) {
                ScoreType.SPARE -> calculateBonus(i + 1, 1)
                ScoreType.STRIKE -> calculateBonus(i + 1, 2)
                else -> 0
            }
        }.sum()
        return score + bonus
    }

    private fun hasEnoughPins(pins: Int): Boolean {
        if (pins < 0 || pins > 10) {
            return false
        }
        val lastFrame = frames.last()
        val score = lastFrame.throws.sum() + pins
        return if (frames.size < 10) {
            score <= 10
        } else {
            when (lastFrame.scoreType) {
                ScoreType.OPEN_FRAME -> score <= 10
                ScoreType.SPARE -> score <= 20
                ScoreType.STRIKE -> (lastFrame.throws.getOrNull(1) ?: pins) == 10 ||
                        ((lastFrame.throws.getOrNull(1) ?: pins) < 10 &&
                                lastFrame.throws.subList(1, lastFrame.throws.size)
                                    .sum() + pins <= 10)

                null -> true
            }
        }
    }

    private fun isGameDone(): Boolean {
        val lastFrame = frames.lastOrNull()
        return if (frames.size == 10 && lastFrame != null) {
            val scoreType = lastFrame.scoreType
            when (scoreType) {
                ScoreType.OPEN_FRAME -> lastFrame.throws.size == 2
                ScoreType.SPARE, ScoreType.STRIKE -> lastFrame.throws.size == 3
                null -> false
            }
        } else {
            false
        }
    }

    private fun calculateBonus(i: Int, n: Int): Int {
        return frames.subList(i, frames.size).flatMap { it.throws }.take(n).sum()
    }
}
