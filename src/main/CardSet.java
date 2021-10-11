package main;

import java.util.ArrayList;

public abstract class CardSet {
    public ArrayList<Card> deck;

    public CardSet () {

    }

    void addCard (Card c) {
        deck.add(c);
    }

    int getDeckSize () {
        return this.deck.size();
    }

    Card getCard (int index) {
        /* return the card at the specified position */
        return this.deck.get(index);
    }

    Card getRandomCard () {
        int idx = (int)(Math.random() * getDeckSize());
        return getCard(idx);
    }


}
