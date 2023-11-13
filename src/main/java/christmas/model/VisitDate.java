package christmas.model;

import static christmas.constant.error.ErrorMessage.INVALID_VISIT_DATE;

public class VisitDate {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;

    private final int day;

    public VisitDate(int day) {
        validateDay(day);
        this.day = day;
    }

    private void validateDay(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public int getDay() {
        return day;
    }
}
