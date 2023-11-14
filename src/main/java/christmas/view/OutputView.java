package christmas.view;

import static christmas.constant.view.OutputMessage.GIVEAWAY_MENU_MESSAGE;
import static christmas.constant.view.OutputMessage.MENU_COUNTS_MESSAGE;
import static christmas.constant.view.OutputMessage.NOTHING;
import static christmas.constant.view.OutputMessage.PREVIEW_MESSAGE;
import static christmas.constant.view.OutputMessage.PRICE_BEFORE_DISCOUNT_MESSAGE;
import static christmas.constant.view.OutputMessage.START_MESSAGE;

import christmas.constant.menu.Menu;
import christmas.dto.discount.DiscountResponse;
import christmas.dto.order.OrderResponse;
import java.util.Map;

public class OutputView {

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printOrderResponse(OrderResponse orderResponse) {
        System.out.println(PREVIEW_MESSAGE);
        System.out.println();

        printMenu(orderResponse.getMenuCounts());

        printPriceBeforeDiscount(orderResponse.getPriceBeforeDiscount());

        printGiveawayMenu(orderResponse.getGiveawayMenu());
    }

    private static void printMenu(Map<Menu, Integer> menuCounts) {
        if (menuCounts.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }

        System.out.println(MENU_COUNTS_MESSAGE);
        menuCounts.forEach((menu, count) -> System.out.printf("%s %d개\n", menu.getFood(), count));
        System.out.println();
    }

    private static void printPriceBeforeDiscount(int priceBeforeDiscount) {
        System.out.println(PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.printf("%d원\n", priceBeforeDiscount);
    }

    private static void printGiveawayMenu(String giveawayMenu) {
        System.out.println(GIVEAWAY_MENU_MESSAGE);
        System.out.println(giveawayMenu);
    }

    public static void printDiscountResponse(DiscountResponse discountResponse) {

    }
}
