package main;

enum Suits {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private final String suit;
    Suits(String s) {
        this.suit = s;
    }

    String getSuit() {
        return this.suit;
    }
}