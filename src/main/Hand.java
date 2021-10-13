package main;

class Hand extends CardSet {
    private int id;
    private Money bet;
    private boolean isStand;
    private boolean isBust;

    Hand(Money bet) {
        super();
        this.bet = bet;
        isBust = false;
        isStand = false;
    }

    public void setBet(Money bet) {
        this.bet = bet;
    }

    public Money getBet() {
        return bet;
    }

    public void setBust(boolean bust) {
        isBust = bust;
    }

    public void setStand(boolean stand) {
        isStand = stand;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isStand() {
        return isStand;
    }

    public void hit(Card c) {

    }

    public Hand split() {
        //TODO: 剩下一半在现在的cardset
        return null;
    }

    public void deal(Card[] cards) {

    }

    public void stand() {
        this.isStand = true;
    }

    public void doubleUp(Card c) {
        //TODO: double bet
    }
}
