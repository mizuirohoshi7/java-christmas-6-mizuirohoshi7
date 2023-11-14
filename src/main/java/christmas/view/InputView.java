package christmas.view;

import static christmas.constant.error.ErrorMessage.INVALID_VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String VISIT_DATE_READ_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_READ_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int readVisitDate() {
        try {
            System.out.println(VISIT_DATE_READ_MESSAGE);
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public static String readOrder() {
        System.out.println(ORDER_READ_MESSAGE);
        return Console.readLine();
    }
}
