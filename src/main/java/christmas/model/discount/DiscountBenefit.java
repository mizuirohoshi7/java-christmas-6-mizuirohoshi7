package christmas.model.discount;

import christmas.constant.discount.DiscountType;
import java.util.Map;

public class DiscountBenefit {

    private final Map<DiscountType, Integer> discountPriceByType;

    public DiscountBenefit(Map<DiscountType, Integer> discountPriceByType) {
        this.discountPriceByType = discountPriceByType;
    }

    public Map<DiscountType, Integer> getDiscountPriceByType() {
        return discountPriceByType;
    }
}
