package main;

class Money {
    int value;

    Money(int initVal) {
        this.value = initVal;
    }

    int getValue () {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    void addValue(Money amount) {
        this.setValue(this.value + amount.getValue());
    }

    void deduct(Money amount) {
        this.setValue(this.value - amount.getValue());
    }

    void doubleUp() {
        /* Double up money */
        this.value = this.value * 2;
    }

    boolean isEmpty() {
        // TODO: 起个什么名字好呢？
        if (this.value <= 0) {
            return true;
        }
        return false;
    }

    boolean compareTo(Money m) {
        if (this.getValue() > m.getValue()) {
            return true;
        }
        return false;
    }

}
