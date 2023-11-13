package christmas.model;

import christmas.constant.menu.Menu;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> menuCounts;

    public Order(Map<Menu, Integer> menuCounts) {
        this.menuCounts = menuCounts;
    }

    public int getPriceBeforeDiscount() {
        return menuCounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
