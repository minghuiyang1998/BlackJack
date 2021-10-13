package main;

abstract class AbstractPlayer {
    private final String name;
    private Money balance;

    AbstractPlayer(String name, int balance) {
        this.name = name;
        this.balance = new Money(balance);
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    private void reset() {
        /* each round init player's cardsInHand and action status */
//        if (this.cardsInHand.getSize() == 0) {
//            return;
//        }
//        clearCardsHold();
    }
}
