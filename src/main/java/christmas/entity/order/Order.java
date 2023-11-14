package christmas.entity.order;

import static christmas.constant.discount.DiscountType.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.constant.discount.DiscountType.GIVEAWAY_DISCOUNT;
import static christmas.constant.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.constant.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.constant.discount.DiscountType.WEEKEND_DISCOUNT;

import christmas.constant.discount.DiscountType;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.Main;
import christmas.constant.menu.Menu;
import christmas.entity.date.VisitDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Order {

    private static final int GIVEAWAY_PRICE = 10000;
    private static final String NOTHING = "없음";
    private static final String GIVEAWAY_MENU = "샴페인 1개";
    private static final List<Integer> WEEKENDS = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int CHRISTMAS_D_DAY_DISCOUNT_END_DAY = 25;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_BY_DAY = 100;

    private final Map<Menu, Integer> menuCounts;

    public Order(Map<Menu, Integer> menuCounts) {
        this.menuCounts = menuCounts;
    }

    public int getPriceBeforeDiscount() {
        return menuCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public String getGiveawayMenu() {
        if (getPriceBeforeDiscount() < GIVEAWAY_PRICE) {
            return NOTHING;
        }
        return GIVEAWAY_MENU;
    }

    public Map<DiscountType, Integer> getDiscountPriceByType(VisitDate visitDate) {
        Map<DiscountType, Integer> discountPriceByType = new HashMap<>();
        if (isTotalPriceLessThanTenThousand()) {
            return discountPriceByType;
        }

        discountPriceByType.put(CHRISTMAS_D_DAY_DISCOUNT, calculateChristmasDDayDiscount(visitDate));
        discountPriceByType.put(WEEKDAY_DISCOUNT, calculateWeekdayDiscount(visitDate));
        discountPriceByType.put(WEEKEND_DISCOUNT, calculateWeekendDiscount(visitDate));
        discountPriceByType.put(SPECIAL_DISCOUNT, calculateSpecialDiscount(visitDate));
        discountPriceByType.put(GIVEAWAY_DISCOUNT, calculateGiveawayDiscount());

        return discountPriceByType;
    }

    private boolean isTotalPriceLessThanTenThousand() {
        return getPriceBeforeDiscount() < 10000;
    }

    private int calculateChristmasDDayDiscount(VisitDate visitDate) {
        if (CHRISTMAS_D_DAY_DISCOUNT_END_DAY < visitDate.getDay()) {
            return 0;
        }
        return CHRISTMAS_D_DAY_DISCOUNT.getPrice() + CHRISTMAS_D_DAY_DISCOUNT_BY_DAY * (visitDate.getDay() - 1);
    }

    private int calculateWeekdayDiscount(VisitDate visitDate) {
        if (WEEKENDS.contains(visitDate.getDay())) {
            return 0;
        }
        return menuCounts.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Dessert)
                .mapToInt(Entry::getValue)
                .sum()
                * WEEKDAY_DISCOUNT.getPrice();
    }

    private int calculateWeekendDiscount(VisitDate visitDate) {
        if (!WEEKENDS.contains(visitDate.getDay())) {
            return 0;
        }
        return menuCounts.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof Main)
                .mapToInt(Entry::getValue)
                .sum()
                * WEEKEND_DISCOUNT.getPrice();
    }

    private int calculateSpecialDiscount(VisitDate visitDate) {
        if (!SPECIAL_DAYS.contains(visitDate.getDay())) {
            return 0;
        }
        return SPECIAL_DISCOUNT.getPrice();
    }

    private int calculateGiveawayDiscount() {
        if (getGiveawayMenu().equals(NOTHING)) {
            return 0;
        }
        return GIVEAWAY_DISCOUNT.getPrice();
    }

    public Map<Menu, Integer> getMenuCounts() {
        return menuCounts;
    }
}
