package christmas.constant.menu;

import static christmas.constant.error.ErrorMessage.INVALID_ORDER;

public enum Drink implements Menu {

    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000)
    ;

    private final String food;
    private final int price;

    Drink(String food, int price) {
        this.food = food;
        this.price = price;
    }

    @Override
    public String getFood() {
        return food;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public static Drink of(String menu) {
        try {
            return valueOf(menu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
