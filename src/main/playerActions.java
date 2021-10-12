package main;

enum playerActions {
    HIT("hi"),
    STAND("st"),
    SPLIT("sp"),
    DOUBLEUP("db"),
    DEAL("dl");

    private final String symbol;
    playerActions(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
