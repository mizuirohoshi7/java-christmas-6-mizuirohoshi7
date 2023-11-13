package christmas.dto.discount;

import static christmas.constant.badge.EventBadge.SANTA;
import static christmas.constant.badge.EventBadge.STAR;
import static christmas.constant.badge.EventBadge.TREE;

import christmas.constant.discount.DiscountType;
import java.util.Map;

public class DiscountResponse {

    private static final String NOTHING = "없음";

    private final Map<DiscountType, Integer> discountPriceByType;
    private final int priceBeforeDiscount;

    public DiscountResponse(Map<DiscountType, Integer> discountPriceByType, int priceBeforeDiscount) {
        deleteZeroDiscountType(discountPriceByType);
        this.discountPriceByType = discountPriceByType;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    private void deleteZeroDiscountType(Map<DiscountType, Integer> discountPriceByType) {
        discountPriceByType.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    public int getTotalDiscountPrice() {
        return discountPriceByType.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getPriceAfterDiscount() {
        return priceBeforeDiscount - getTotalDiscountPrice();
    }

    public String getEventBadge() {
        if (SANTA.getPrice() <= getTotalDiscountPrice()) {
            return SANTA.getName();
        }
        if (TREE.getPrice() <= getTotalDiscountPrice()) {
            return TREE.getName();
        }
        if (STAR.getPrice() <= getTotalDiscountPrice()) {
            return STAR.getName();
        }
        return NOTHING;
    }

    public Map<DiscountType, Integer> getDiscountPriceByType() {
        return discountPriceByType;
    }
}
