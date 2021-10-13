package main;

class Split {
    private final int id;
    private CardSet cardSet;
    private Money bet;
    private boolean stand;

    Split(int id, CardSet cardSet, Money bet) {
        this.id = id;
        this.cardSet = cardSet;
        this.bet = bet;
        this.stand = false;
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

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public Money getBet() {
        return bet;
    }

    public void setBet(Money bet) {
        this.bet = bet;
    }
}
