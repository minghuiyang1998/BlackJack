package main;

public enum CardGameType {
    BJ(0,"BlackJack") {
        BlackJack getGame() {
            return BlackJack.getInstance();
        }
    },
    TE(1, "TriantaEna") {
         TriantaEna getGame() {
            return TriantaEna.getInstance();
        }
    };

    abstract AbstractCardGame getGame();

    private final int id;
    private final String name;

    CardGameType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }
}
