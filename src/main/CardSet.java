package main;

import java.util.ArrayList;

class CardSet {
    private ArrayList<Card> deck;

    public CardSet() {
        deck = new ArrayList<Card>();
    }

    void addCard(Card c) {
        deck.add(c);
    }

    int getDeckSize() {
        return this.deck.size();
    }

    Card getCard(int index) {
        /* return the card at the specified position */
        return this.deck.get(index);
    }

    void removeCard(int index) {
        this.deck.remove(index);
    }

    void clear() {
        this.deck.clear();
    }

}
