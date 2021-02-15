package automation.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DateHelper {

    public static LocalDate getNextDay(LocalDate date, String day) {
        return date.with(TemporalAdjusters.next(DayOfWeek.valueOf(day)));
    }
}
