package christmas.constant.menu;

import java.util.Arrays;

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

    public static Appetizer of(String food) {
        return Arrays.stream(Appetizer.values())
                .filter(appetizer -> appetizer.getFood().equals(food))
                .findFirst()
                .orElse(null);
    }
}
