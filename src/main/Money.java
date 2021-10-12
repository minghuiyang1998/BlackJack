package main;

class Money {
    private int value;

    Money(int initVal) {
        this.value = initVal;
    }

    public int getValue () {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void doubleUp() {
        value *= 2;
    }
}
