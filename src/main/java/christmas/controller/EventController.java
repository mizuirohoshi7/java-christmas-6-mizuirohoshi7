package christmas.controller;

import static christmas.constant.error.ErrorMessage.INVALID_ORDER;

import christmas.constant.error.ErrorMessage;
import christmas.constant.menu.Appetizer;
import christmas.constant.menu.Dessert;
import christmas.constant.menu.Drink;
import christmas.constant.menu.Main;
import christmas.constant.menu.Menu;
import christmas.dto.discount.DiscountResponse;
import christmas.dto.order.OrderResponse;
import christmas.entity.date.VisitDate;
import christmas.entity.order.Order;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class EventController {

    private static EventController eventController;
    private final EventService eventService = EventService.getInstance();

    private EventController() {}

    public static EventController getInstance() {
        if (eventController == null) {
            eventController = new EventController();
        }
        return eventController;
    }

    public void start() {
        OutputView.printStartMessage();
        VisitDate visitDate;
        do {
            visitDate = createVisitDate();
        } while (visitDate == null);

        Order order;
        do {
            order = createOrder();
        } while (order == null);

        OutputView.printOrderResponse(new OrderResponse(order));

        calculateDiscount(visitDate, order);
    }

    private VisitDate createVisitDate() {
        try {
            return new VisitDate(InputView.readVisitDate());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    private Order createOrder() {
        try {
            String orderInput = InputView.readOrder();
            isValidOrderInput(orderInput);
            Map<Menu, Integer> menuCounts = createMenuCounts(orderInput);
            isNotOnlyDrink(menuCounts);
            isNotOverTwenty(menuCounts);
            return new Order(menuCounts);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    // "메뉴-수량,메뉴-수량..." 형식이 아닌 경우 예외 발생
    private void isValidOrderInput(String orderInput) {
        if (!orderInput.matches("([가-힣]+-[0-9]+,)*[가-힣]+-[0-9]+")) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private Map<Menu, Integer> createMenuCounts(String input) {
        Map<String, Integer> menuCounts = new HashMap<>();

        String[] menuOrders = input.split(",");
        for (String menuOrder : menuOrders) {
            String[] menuAndCount = menuOrder.split("-");
            isDuplicatedMenu(menuCounts, menuAndCount[0]);
            isOverOne(menuAndCount[1]);
            menuCounts.put(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
        }

        return toMenuMap(menuCounts);
    }

    private void isDuplicatedMenu(Map<String, Integer> menuCounts, String menu) {
        if (menuCounts.containsKey(menu)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void isOverOne(String count) {
        if (Integer.parseInt(count) < 1) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private Map<Menu, Integer> toMenuMap(Map<String, Integer> menuCounts) {
        Map<Menu, Integer> menuMap = new HashMap<>();
        menuCounts.forEach((menu, count) -> menuMap.put(toMenu(menu), count));
        return menuMap;
    }

    private Menu toMenu(String menu) {
        if (Appetizer.of(menu) != null) {
            System.out.println(menu);
            return Appetizer.of(menu);
        }
        if (Dessert.of(menu) != null) {
            System.out.println(menu);

            return Dessert.of(menu);
        }
        if (Main.of(menu) != null) {
            System.out.println(menu);

            return Main.of(menu);
        }
        if (Drink.of(menu) != null) {
            System.out.println(menu);

            return Drink.of(menu);
        }
        System.out.println("no");
        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
    }

    private void isNotOnlyDrink(Map<Menu, Integer> menuCounts) {
        if (menuCounts.keySet().stream().allMatch(menu -> menu instanceof Drink)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void isNotOverTwenty(Map<Menu, Integer> menuCounts) {
        if (menuCounts.values().stream().mapToInt(Integer::intValue).sum() > 20) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public void calculateDiscount(VisitDate visitDate, Order order) {
        DiscountResponse discountResponse = eventService.createDiscountResponse(visitDate, order);
        OutputView.printDiscountResponse(discountResponse);
    }
}
