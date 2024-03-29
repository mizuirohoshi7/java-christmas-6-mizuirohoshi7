package christmas.constant.menu;

import java.util.Arrays;

public enum Main implements Menu {

    T_BONE_STAKE("티본스테이크", 55000),
    BARBEQUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000)
    ;

    private final String food;
    private final int price;

    Main(String food, int price) {
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

    public static Main of(String food) {
        return Arrays.stream(Main.values())
                .filter(main -> main.food.equals(food))
                .findFirst()
                .orElse(null);
    }
}
