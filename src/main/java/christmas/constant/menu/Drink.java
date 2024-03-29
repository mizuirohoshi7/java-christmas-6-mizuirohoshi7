package christmas.constant.menu;

import java.util.Arrays;

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

    public static Drink of(String food) {
        return Arrays.stream(Drink.values())
                .filter(drink -> drink.food.equals(food))
                .findFirst()
                .orElse(null);
    }
}
