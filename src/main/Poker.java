package main;

import static main.Suits.*;

public class Poker extends CardSet {
    boolean joker;
    Suits[] suits = {HEARTS, DIAMONDS, CLUBS, SPADES};
    private static Poker instance = new Poker();

    private Poker () {
        int n = 10;

        for (int i = 0; i < suits.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    this.addCard(new Card(suits[i], "A", j));
                    continue;
                }
                this.addCard(new Card(suits[i], j + "", j));
            }
            this.addCard(new Card(suits[i], "J", 10));
            this.addCard(new Card(suits[i], "Q", 10));
            this.addCard(new Card(suits[i], "K", 10));
        }

    }


    public Poker (int jack, int queen, int king) {
        /* set J, Q, K with specified value */

        int n = 10;

        for (int i = 0; i < suits.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) {
                    this.addCard(new Card(suits[i], "Ace", j));
                    continue;
                }
                this.addCard(new Card(suits[i], j + "", j));
            }
            this.addCard(new Card(suits[i], "Jack", jack));
            this.addCard(new Card(suits[i], "Queen", queen));
            this.addCard(new Card(suits[i], "King", king));
        }
    }
}
