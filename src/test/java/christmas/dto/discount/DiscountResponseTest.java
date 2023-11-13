package christmas.dto.discount;

import static christmas.constant.badge.EventBadge.TREE;
import static christmas.constant.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.constant.discount.DiscountType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.constant.discount.DiscountType;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountResponseTest {

    static DiscountResponse discountResponse;

    @BeforeAll
    static void setUp() {
        Map<DiscountType, Integer> discountPriceByType = Map.of(
                WEEKDAY_DISCOUNT, WEEKDAY_DISCOUNT.getPrice() * 3,
                WEEKEND_DISCOUNT, WEEKEND_DISCOUNT.getPrice() * 2
        );
        int priceBeforeDiscount = 100000;
        discountResponse = new DiscountResponse(discountPriceByType, priceBeforeDiscount);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    void 할인_후_예상_결제_금액_계산_성공() {
        int priceAfterDiscount = discountResponse.getPriceAfterDiscount();

        assertThat(priceAfterDiscount).isEqualTo(
                100000 - WEEKDAY_DISCOUNT.getPrice() * 3 - WEEKEND_DISCOUNT.getPrice() * 2);
    }

    @Test
    @DisplayName("총 혜택 금액으로 이벤트 배지를 알아낸다.")
    void 총_혜택_금액으로_이벤트_배지_알아내기() {
        String eventBadge = discountResponse.getEventBadge();

        assertThat(eventBadge).isEqualTo(TREE.getName());
    }
}