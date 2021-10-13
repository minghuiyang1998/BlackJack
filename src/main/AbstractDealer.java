package main;

abstract class AbstractDealer {
    CardSet notDealt;

    AbstractDealer(CardSet deck) {
        this.notDealt = deck;
    }

    Card[] deal(int n) {
        /* deal n cards to a player */
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) {
            cards[i] = getRandomCard();
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
