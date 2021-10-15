package main;

class Card {
    private final Suits suit;    // For poker, suits can be clubs, spades, hearts and diamonds
    private final String name;    // Ace, 2, ..., 10, Jack, Queen, King
    private final int value;
    private boolean isShown;

    Card(Suits suit, String name, int val) {
        this.suit = suit;
        this.name = name;
        this.value = val;
        this.isShown = true;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public boolean isShown() {
        return isShown;
    }

    @Override
    public String toString() {
        return getSuit().toString() + getName();
    }

    public Suits getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
