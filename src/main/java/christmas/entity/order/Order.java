package christmas.entity.order;

import static christmas.constant.discount.DiscountType.CHRISTMAS_D_DAY_DISCOUNT;
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

    public Map<DiscountType, Integer> getDiscountPriceByType(VisitDate visitDate) {
        Map<DiscountType, Integer> discountPriceByType = new HashMap<>();

        discountPriceByType.put(CHRISTMAS_D_DAY_DISCOUNT, calculateChristmasDDayDiscount(visitDate));
        discountPriceByType.put(WEEKDAY_DISCOUNT, calculateWeekdayDiscount(visitDate));
        discountPriceByType.put(WEEKEND_DISCOUNT, calculateWeekendDiscount(visitDate));
        discountPriceByType.put(SPECIAL_DISCOUNT, calculateSpecialDiscount(visitDate));

        return discountPriceByType;
    }

    private int calculateChristmasDDayDiscount(VisitDate visitDate) {
        if (CHRISTMAS_D_DAY_DISCOUNT_END_DAY < visitDate.getDay()) {
            return 0;
        }
        return CHRISTMAS_D_DAY_DISCOUNT.getPrice() + CHRISTMAS_D_DAY_DISCOUNT_BY_DAY * visitDate.getDay();
    }

    private int calculateWeekdayDiscount(VisitDate visitDate) {
        if (WEEKENDS.contains(visitDate.getDay())) {
            return 0;
        }
        return (int) menuCounts.keySet().stream()
                .filter(menu -> menu instanceof Dessert)
                .count()
                * WEEKDAY_DISCOUNT.getPrice();
    }

    private int calculateWeekendDiscount(VisitDate visitDate) {
        if (!WEEKENDS.contains(visitDate.getDay())) {
            return 0;
        }
        return (int) menuCounts.keySet().stream()
                .filter(menu -> menu instanceof Main)
                .count()
                * WEEKEND_DISCOUNT.getPrice();
    }

    private int calculateSpecialDiscount(VisitDate visitDate) {
        if (!SPECIAL_DAYS.contains(visitDate.getDay())) {
            return 0;
        }
        return SPECIAL_DISCOUNT.getPrice();
    }
}
