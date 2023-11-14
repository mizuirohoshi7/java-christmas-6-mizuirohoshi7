package christmas.constant.menu;

import static christmas.constant.error.ErrorMessage.INVALID_ORDER;

public enum Dessert implements Menu {

    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000)
    ;

    private final String food;
    private final int price;

    Dessert(String food, int price) {
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

    public static Dessert of(String menu) {
        try {
            return valueOf(menu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
