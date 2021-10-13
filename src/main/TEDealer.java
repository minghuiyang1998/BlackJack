package main;

class TEDealer extends AbstractDealer {
    CardSet cardsInHand;

    TEDealer(Poker deck) {
        super(deck);
        this.cardsInHand = new CardSet();
    }

}
