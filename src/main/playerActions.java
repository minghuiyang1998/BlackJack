package main;

enum playerActions {
    HIT("hit", 1),
    STAND("stand", 2),
    SPLIT("split", 3),
    DOUBLEUP("double up", 4),
    DEAL("deal", 0);

    private final String symbol;
    private final int id;
    playerActions(String symbol, int id) {
        this.symbol = symbol;
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getId() {return id;}
}
