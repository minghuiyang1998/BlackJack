package main;

enum PlayerActions {
    HIT(0, "hit"),
    STAND(1, "stand"),
    SPLIT(2, "split"),
    DOUBLEUP(3, "doubleup"),
    DEAL(4, "deal");

    private final int id;
    private final String symbol;
    PlayerActions(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getId() {
        return id;
    }
}
