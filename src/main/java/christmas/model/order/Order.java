package christmas.model.order;

import christmas.constant.menu.Menu;
import java.util.Map;

public class Order {

    private static final int GIVEAWAY_PRICE = 10000;
    private static final String NOTHING = "없음";
    private static final String GIVEAWAY_MENU = "샴페인 1개";

    private final Map<Menu, Integer> menuCounts;

    public Order(Map<Menu, Integer> menuCounts) {
        this.menuCounts = menuCounts;
    }

    public int getPriceBeforeDiscount() {
        return menuCounts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public String getGiveawayMenu() {
        if (getPriceBeforeDiscount() < GIVEAWAY_PRICE) {
            return NOTHING;
        }
        return GIVEAWAY_MENU;
    }
}
