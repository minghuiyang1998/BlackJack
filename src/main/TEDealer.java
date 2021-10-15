package main;

class TEDealer extends AbstractDealer {
    String name;
    Money bank;
    Money bet;

    TEDealer(String name, int balance, Poker deck) {
        super(deck, new Hand(new Money(0)));
        this.name = name;
        this.bank = new Money(balance);
    }

    void addCard(Card c) {
        /* dealer get cards after all players stand/bust */
        getHand().hit(c);
    }

    public String getName() {
        return name;
    }

    public Money getBank() {
        return bank;
    }

    public void setBank(Money bank) {
        this.bank = bank;
    }

    public Money getBet() {
        return bet;
    }

    public void setBet(Money bet) {
        this.bet = bet;
        this.getHand().setBet(bet);
    }
}
