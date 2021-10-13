package main;

final class TEPlayer extends AbstractPlayer {
    private Hand hand;

    TEPlayer(String name, int balance) {
            super(name, balance);
    }

    public Hand getHand() {
        return hand;
    }

    public void setBet(Money bet) {
        hand = new Hand(bet);
    }

    public void hit(Card c) {
        hand.hit(c);
    }

    public void deal(Card[]cards) {
        hand.deal(cards);
    }

    public void standCurr() {
        hand.stand();
    }

    public void doubleUp(Card c) {
        hand.doubleUp(c);
    }

    public void setCurrBust(boolean bust) {
        hand.setBust(bust);
    }

    public boolean isCurrBust() {
        return hand.isBust();
    }
    public boolean isCurrStand() {
        return hand.isStand();
    }
}
