package christmas.dto.order;

import java.util.Map;

public class OrderResponse {

    private final Map<String, Integer> menuCounts;
    private final int totalPrice;
    private final String giveawayMenu;

    public OrderResponse(Map<String, Integer> menuCounts, int totalPrice, String giveawayMenu) {
        this.menuCounts = menuCounts;
        this.totalPrice = totalPrice;
        this.giveawayMenu = giveawayMenu;
    }

    public Map<String, Integer> getMenuCounts() {
        return menuCounts;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getGiveawayMenu() {
        return giveawayMenu;
    }
}
