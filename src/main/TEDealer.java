package main;

class TEDealer extends AbstractDealer {
    TEDealer(Poker deck) {
        super(deck, new Hand(new Money(0)));
    }
}
