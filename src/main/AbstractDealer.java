package main;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

abstract class AbstractDealer {
    private CardSet notDealt;
    private Hand hand;

    AbstractDealer(CardSet deck, Hand hand) {
        this.notDealt = deck;
        this.hand = hand;
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
        int idx = ThreadLocalRandom.current().nextInt(notDealt.getSize());
        Card c = notDealt.removeCard(idx);
        return c;
    }

    public Hand getHand() {
        return hand;
    }

    void setNotDealt(CardSet deck) {
        this.notDealt = deck;
    }

    void reset() {
        this.getHand().clear();
        this.setNotDealt(new Poker());
    }
}
