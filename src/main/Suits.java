package main;

public enum Suits {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private final String suit;
    Suits(String s) {
        this.suit = s;
    }

    public String getSuit() {
        return this.suit;
    }
}