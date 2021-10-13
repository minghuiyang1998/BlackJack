package main;

enum PlayerActionType {
    HIT("hit"),
    STAND("stand"),
    SPLIT("split"),
    DOUBLEUP("doubleup"),
    DEAL("deal");

    private final String name;
    PlayerActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
