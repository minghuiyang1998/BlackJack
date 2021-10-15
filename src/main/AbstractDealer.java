package main;

import java.util.ArrayList;

abstract class AbstractDealer {
    CardSet notDealt;

    AbstractDealer(CardSet deck) {
        this.notDealt = deck;
    }

    ArrayList<Card> deal(int n) {
        /* deal n cards to a player */
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cards.add(getRandomCard());
        }

        return cards;
    }

    Card getRandomCard() {
        /* get a random card from notDealt cardset */
        int idx = (int)(Math.random() * notDealt.getSize());
        Card c = notDealt.getCard(idx);
        notDealt.removeCard(idx);

        return c;
    }

    void setNotDealt(CardSet deck) {
        this.notDealt = deck;
    }




}
