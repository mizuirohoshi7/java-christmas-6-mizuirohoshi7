package christmas;

import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        EventController eventController = EventController.getInstance();
        eventController.start();
    }
}
