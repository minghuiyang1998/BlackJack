package main;

final class Poker extends CardSet {
    private static Poker INSTANCE = null;

    public static Poker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Poker();
        }
        return INSTANCE;
    }

    private Poker() {
        int n = 10;

        for (int i = 0; i < Suits.values().length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    this.addCard(new Card(Suits.values()[i], "A", j));
                    continue;
                }
                this.addCard(new Card(Suits.values()[i], String.valueOf(j), j));
            }
            this.addCard(new Card(Suits.values()[i], "J", 10));
            this.addCard(new Card(Suits.values()[i], "Q", 10));
            this.addCard(new Card(Suits.values()[i], "K", 10));
        }

    }

}
