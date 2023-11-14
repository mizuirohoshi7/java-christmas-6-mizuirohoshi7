package christmas.constant.discount;

public enum DiscountType {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 1000),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023),
    SPECIAL_DISCOUNT("특별 할인", 1000),
    GIVEAWAY_DISCOUNT("증정 이벤트", 25000)
    ;

    private final String name;
    private final int price;

    DiscountType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
