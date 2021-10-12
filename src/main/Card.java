package main;

class Card {
    private Suits suit;    // For poker, suits can be clubs, spades, hearts and diamonds
    private String name;    // Ace, 2, ..., 10, Jack, Queen, King
    private int value;

    Card(Suits suit, String name, int val) {
        this.suit = suit;
        this.name = name;
        this.value = val;
    }

    @Override
    public String toString() {
        return getSuit().toString() + getName();
    }

    public boolean equals(Card c) {
        return this.value == c.getValue();
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
