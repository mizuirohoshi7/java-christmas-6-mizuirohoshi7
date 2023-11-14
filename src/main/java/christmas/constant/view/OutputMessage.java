package christmas.constant.view;

public enum OutputMessage {

    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PREVIEW_MESSAGE("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    MENU_COUNTS_MESSAGE("<주문 메뉴>"),
    PRICE_BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU_MESSAGE("<증정 메뉴>"),
    DISCOUNT_BENEFIT_MESSAGE("<혜택 내역>"),
    TOTAL_DISCOUNT_PRICE_MESSAGE("<총혜택 금액>"),
    PRICE_AFTER_DISCOUNT_MESSAGE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE_MESSAGE("<12월 이벤트 배지>"),
    NOTHING("없음")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
