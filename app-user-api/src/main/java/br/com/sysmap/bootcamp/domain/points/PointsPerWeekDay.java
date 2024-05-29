package br.com.sysmap.bootcamp.domain.points;

import java.time.DayOfWeek;

public enum PointsPerWeekDay {
    MONDAY(7L),
    TUESDAY(6L),
    WEDNESDAY(2L),
    THURSDAY(10L),
    FRIDAY(15L),
    SATURDAY(20L),
    SUNDAY(25L);

    private Long points;

    PointsPerWeekDay(Long points) {
        this.points = points;
    }

    public Long getPointsPerWeekDay() {
        return points;
    }

    public static Long getPointsPerWeekDay(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return MONDAY.getPointsPerWeekDay();
            case TUESDAY:
                return TUESDAY.getPointsPerWeekDay();
            case WEDNESDAY:
                return WEDNESDAY.getPointsPerWeekDay();
            case THURSDAY:
                return THURSDAY.getPointsPerWeekDay();
            case FRIDAY:
                return FRIDAY.getPointsPerWeekDay();
            case SATURDAY:
                return SATURDAY.getPointsPerWeekDay();
            case SUNDAY:
                return SUNDAY.getPointsPerWeekDay();
            default:
                return 0L;
        }
    }

}