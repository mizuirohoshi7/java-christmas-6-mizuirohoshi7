package christmas.constant.badge;

public enum EventBadge {

    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000)
    ;

    private final String name;
    private final int price;

    EventBadge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
