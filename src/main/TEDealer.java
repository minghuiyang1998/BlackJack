package main;

class TEDealer extends AbstractDealer {
    Money bank;
    Money bet;
    TEDealer(Poker deck) {
        super(deck, new Hand(new Money(0)));
    }

    void addCard(Card c) {
        /* dealer get cards after all players stand/bust */
        getHand().hit(c);
    }
}
