package main;

import java.util.ArrayList;

class CardSet {
    private ArrayList<Card> deck;

    public CardSet() {
        deck = new ArrayList<Card>();
    }

    public void addCard(Card c) {
        deck.add(c);
    }

    public int getSize() {
        return deck.size();
    }

    Card getCard(int index) {
        /* return the card at the specified position */
        return deck.get(index);
    }

    Card removeCard(int index) {
        return deck.remove(index);
    }

    void clear() {
        deck.clear();
    }

    ArrayList<Card> getDeck() {
        return this.deck;
    }
}
