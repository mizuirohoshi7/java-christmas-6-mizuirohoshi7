package christmas.view;

import static christmas.constant.error.ErrorMessage.INVALID_VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static int readVisitDate() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }
}
