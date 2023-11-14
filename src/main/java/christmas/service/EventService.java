package christmas.service;

import christmas.constant.discount.DiscountType;
import christmas.dto.discount.DiscountResponse;
import christmas.entity.date.VisitDate;
import christmas.entity.order.Order;
import java.util.Map;

public class EventService {

    private static EventService eventService;

    private EventService() {}

    public static EventService getInstance() {
        if (eventService == null) {
            eventService = new EventService();
        }
        return eventService;
    }

    public DiscountResponse createDiscountResponse(VisitDate visitDate, Order order) {
        Map<DiscountType, Integer> discountPriceByType = order.getDiscountPriceByType(visitDate);
        return new DiscountResponse(discountPriceByType, order.getPriceBeforeDiscount());
    }
}
