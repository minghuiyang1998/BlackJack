package main;

abstract class AbstractPlayer {
    private final String name;
    private Money balance;

    AbstractPlayer(String name, int balance) {
        this.name = name;
        this.balance = new Money(balance);
    }

    public int getBalance() {
        return balance.getValue();
    }

    public String getName() {
        return name;
    }

    public void setBalance(int balance) {
        this.balance = new Money(balance);
    }

    abstract public void reset();
}
