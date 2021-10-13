package main;

class BJDealer extends AbstractDealer {
    CardSet cardsInHand;

    BJDealer(Poker deck) {
        super(deck);
        this.cardsInHand = new CardSet();

    }

    void addCard(Card c) {
        /* dealer get cards after all players stand/bust */
        this.cardsInHand.addCard(c);
    }

}