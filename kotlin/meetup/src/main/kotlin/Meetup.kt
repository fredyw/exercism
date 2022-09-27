import java.time.DayOfWeek
import java.time.LocalDate

class Meetup(private val month: Int, private val year: Int) {
    private val dates: List<LocalDate> =
        generateSequence(LocalDate.of(year, month, 1)) { date ->
            date.plusDays(1).let { if (date.month == it.month) it else null }
        }.toList()

    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule): LocalDate{
        return dates.filter { it.dayOfWeek == dayOfWeek }.let { date ->
            when (schedule) {
                MeetupSchedule.FIRST -> date.first()
                MeetupSchedule.SECOND -> date.drop(1).first()
                MeetupSchedule.THIRD -> date.drop(2).first()
                MeetupSchedule.FOURTH -> date.drop(3).first()
                MeetupSchedule.LAST -> date.last()
                MeetupSchedule.TEENTH -> date.find { it.dayOfMonth > 12 }!!
            }
        }
    }
}
