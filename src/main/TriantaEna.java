package main;

class TriantaEna extends AbstractCardGame {
    private static TriantaEna INSTANCE = null;
    private TriantaEna() {
        super();
    }
    static public TriantaEna getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TriantaEna();
        }
        return INSTANCE;
    }

    @Override
    void startGame() {
        System.out.println("TriantaEna starts");
    }
}
