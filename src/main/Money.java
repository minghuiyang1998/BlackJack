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

    void addValue(int amount) {
        this.setValue(this.value + amount);
    }

    void deduct(int amount) {
        this.setValue(this.value - amount);
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

}
