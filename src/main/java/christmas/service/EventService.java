package christmas.service;

import christmas.dto.discount.DiscountResponse;
import christmas.entity.date.VisitDate;
import christmas.entity.order.Order;

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
        return null;
    }
}
