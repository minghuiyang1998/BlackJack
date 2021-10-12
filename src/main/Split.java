package main;

class Split {
    private final int id;
    private CardSet cardSet;
    private Money bet;

    Split(int id, CardSet cardSet, Money bet) {
        this.id = id;
        this.cardSet = cardSet;
        this.bet = bet;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public int getId() {
        return id;
    }

    public Money getBet() {
        return bet;
    }

    public void setBet(Money bet) {
        this.bet = bet;
    }
}
