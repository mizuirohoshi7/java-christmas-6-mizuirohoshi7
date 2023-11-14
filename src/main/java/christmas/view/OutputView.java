package christmas.view;

import static christmas.constant.view.OutputMessage.DISCOUNT_BENEFIT_MESSAGE;
import static christmas.constant.view.OutputMessage.EVENT_BADGE_MESSAGE;
import static christmas.constant.view.OutputMessage.GIVEAWAY_MENU_MESSAGE;
import static christmas.constant.view.OutputMessage.MENU_COUNTS_MESSAGE;
import static christmas.constant.view.OutputMessage.NOTHING;
import static christmas.constant.view.OutputMessage.PREVIEW_MESSAGE;
import static christmas.constant.view.OutputMessage.PRICE_AFTER_DISCOUNT_MESSAGE;
import static christmas.constant.view.OutputMessage.PRICE_BEFORE_DISCOUNT_MESSAGE;
import static christmas.constant.view.OutputMessage.START_MESSAGE;
import static christmas.constant.view.OutputMessage.TOTAL_DISCOUNT_PRICE_MESSAGE;

import christmas.constant.discount.DiscountType;
import christmas.constant.menu.Menu;
import christmas.dto.discount.DiscountResponse;
import christmas.dto.order.OrderResponse;
import java.text.NumberFormat;
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
        printDiscountBenefit(discountResponse.getDiscountPriceByType());

        printTotalDiscountPrice(discountResponse.getTotalDiscountPrice());

        printPriceAfterDiscount(discountResponse.getPriceAfterDiscount());

        printEventBadge(discountResponse.getEventBadge());
    }

    private static void printDiscountBenefit(Map<DiscountType, Integer> discountPriceByType) {
        System.out.println(DISCOUNT_BENEFIT_MESSAGE);
        discountPriceByType.forEach((discountType, discountPrice) ->
                System.out.printf(
                        "%s: -%s원\n",
                        discountType.getName(), NumberFormat.getInstance().format(discountPrice))
        );
        System.out.println();
    }

    private static void printTotalDiscountPrice(int totalDiscountPrice) {
        System.out.println(TOTAL_DISCOUNT_PRICE_MESSAGE);
        System.out.printf("-%s원\n", NumberFormat.getInstance().format(totalDiscountPrice));
        System.out.println();
    }

    private static void printPriceAfterDiscount(int priceAfterDiscount) {
        System.out.println(PRICE_AFTER_DISCOUNT_MESSAGE);
        System.out.printf("%s원\n", NumberFormat.getInstance().format(priceAfterDiscount));
        System.out.println();
    }

    private static void printEventBadge(String eventBadge) {
        System.out.println(EVENT_BADGE_MESSAGE);
        System.out.println(eventBadge);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
