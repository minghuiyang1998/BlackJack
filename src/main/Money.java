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

    public void add(Money m) {
        this.setValue(this.getValue() + m.getValue());
    }
}
