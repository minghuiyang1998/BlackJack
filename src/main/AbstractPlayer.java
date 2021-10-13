package main;

abstract class AbstractPlayer {
    private final String name;
    private Money balance;
    private Money bet;
    private CardSet cardsInHand;
    private boolean stand;

    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
    public Money getBet() { return bet; }
    public void setBet(Money bet) { this.bet = bet; }


    AbstractPlayer(String name, int balance) {
        this.name = name;
        this.balance = new Money(balance);
        this.bet = new Money(0);
        this.cardsInHand = new CardSet();
        this.stand = false;
    }

    public CardSet getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(CardSet cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    private void clearCardsHold() {
        cardsInHand.clear();
    }

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    private void init() {
        /* each round init player's cardsInHand and action status */
        if (this.cardsInHand.getSize() == 0) {
            return;
        }
        clearCardsHold();
    }

    public void deal(Card[] cards) {
        for (Card c: cards) {
            cardsInHand.addCard(c);
        }
    }

    public void hit(Card c) {
        cardsInHand.addCard(c);
    }

    public void stand() {
        setStand(true);
    }
}
