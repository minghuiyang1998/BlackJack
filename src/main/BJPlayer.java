package main;

final class BJPlayer extends AbstractPlayer {
    boolean isSplit;

    BJPlayer(String name, int balance) {
        super(name, balance);
    }

    void split(CardSet hand) throws IllegalStateException {
        /* split cards into two hands of cards */
        if (hand.getDeckSize() != 2 || !hand.getCard(0).equals(hand.getCard(1)) || !this.balance.compareTo(this.bet)){
            // TODO: throws exception?
            return;
        }

        this.addBet();
        CardSet split = new CardSet();
        split.addCard(hand.getCard(1));
        hand.removeCard(1);
        this.cardsInHand.add(split);

        /* after spliting, player automatically hits */
        // TODO: to hit here? or somewhere else?

    }

    void doubleUp(CardSet hand, Card c) {
        if (hand.getDeckSize() > 2) {
            // TODO: throws exception? IllegalStateException(?)
            return;
        }
        this.addBet();
        hit(hand, c);
    }
}
