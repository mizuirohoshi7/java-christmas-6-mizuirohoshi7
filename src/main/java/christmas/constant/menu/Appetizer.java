package christmas.constant.menu;

import static christmas.constant.error.ErrorMessage.INVALID_ORDER;

public enum Appetizer implements Menu {

    MUSHROOM_SOUP("양송이 수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000)
    ;

    private final String food;
    private final int price;

    Appetizer(String food, int price) {
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

    public static Appetizer of(String menu) {
        try {
            return valueOf(menu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
