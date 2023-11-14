package christmas.dto.order;

import christmas.constant.menu.Menu;
import christmas.entity.order.Order;
import java.util.Map;

public class OrderResponse {

    private final Map<Menu, Integer> menuCounts;
    private final String giveawayMenu;

    public OrderResponse(Order order) {
        this.menuCounts = order.getMenuCounts();
        this.giveawayMenu = order.getGiveawayMenu();
    }

    public int getPriceBeforeDiscount() {
        return menuCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Menu, Integer> getMenuCounts() {
        return menuCounts;
    }

    public String getGiveawayMenu() {
        return giveawayMenu;
    }
}
