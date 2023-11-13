package christmas.entity.order;

import static christmas.constant.discount.DiscountType.SPECIAL_DISCOUNT;
import static christmas.constant.discount.DiscountType.WEEKDAY_DISCOUNT;
import static christmas.constant.discount.DiscountType.WEEKEND_DISCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.constant.discount.DiscountType;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.Main;
import christmas.constant.menu.Menu;
import christmas.entity.date.VisitDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderTest {

    static Order order;

    @BeforeAll
    static void setUp() {
        Map<Menu, Integer> menuCounts = Map.of(
                Main.T_BONE_STAKE, 2,
                Dessert.CHOCOLATE_CAKE, 3
        );
        order = new Order(menuCounts);
    }

    @Test
    @DisplayName("할인 전 총 주문 금액을 계산한다.")
    void 할인_전_총_주문_금액_계산_성공() {
        int priceBeforeDiscount = order.getPriceBeforeDiscount();

        assertThat(priceBeforeDiscount).isEqualTo(calculateTotalPrice(order.getMenuCounts()));
    }

    @Test
    @DisplayName("증정 메뉴 여부를 확인한다.")
    void 증정_메뉴_여부_확인() {
        String giveawayMenu = order.getGiveawayMenu();

        assertThat(giveawayMenu).isEqualTo("샴페인 1개");
    }

    @Nested
    @DisplayName("방문 날짜에 따라 할인 금액을 계산한다.")
    class CalculateDiscountPriceByType {
        @Test
        @DisplayName("평일 할인 금액을 계산한다.")
        void 평일_할인_금액_계산_성공() {
            Map<DiscountType, Integer> discountPriceByType = order.getDiscountPriceByType(new VisitDate(5));

            assertThat(discountPriceByType.get(WEEKDAY_DISCOUNT)).isEqualTo(WEEKDAY_DISCOUNT.getPrice() * 3);
        }

        @Test
        @DisplayName("주말 할인 금액을 계산한다.")
        void 주말_할인_금액_계산_성공() {
            Map<DiscountType, Integer> discountPriceByType = order.getDiscountPriceByType(new VisitDate(2));

            assertThat(discountPriceByType.get(WEEKEND_DISCOUNT)).isEqualTo(WEEKEND_DISCOUNT.getPrice() * 2);
        }

        @Test
        @DisplayName("크리스마스 디데이 할인 금액을 계산한다.")
        void 크리스마스_디데이_할인_금액_계산_성공() {
            Map<DiscountType, Integer> discountPriceByType = order.getDiscountPriceByType(new VisitDate(25));

            assertThat(discountPriceByType.get(DiscountType.CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(3400);
        }

        @Test
        @DisplayName("특별 할인 금액을 계산한다.")
        void 특별_할인_금액_계산_성공() {
            Map<DiscountType, Integer> discountPriceByType = order.getDiscountPriceByType(new VisitDate(3));

            assertThat(discountPriceByType.get(DiscountType.SPECIAL_DISCOUNT)).isEqualTo(SPECIAL_DISCOUNT.getPrice());
        }
    }

    private int calculateTotalPrice(Map<Menu, Integer> menuCounts) {
        return menuCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}