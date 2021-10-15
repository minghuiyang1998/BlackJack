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

    public String getName() {
        return name;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    abstract public void reset();
}
