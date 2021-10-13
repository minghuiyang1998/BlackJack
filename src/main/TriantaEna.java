package main;

import java.util.Scanner;

class TriantaEna extends AbstractCardGame {
    private static TriantaEna INSTANCE = null;

    private static int inquirePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount for players(up to 9): ");
        final int MAX = 9;
        final int MIN = 1;
        int x = 0;
        while (x >= MAX || x < MIN) {
            String r = scanner.nextLine();
            try {
                x = Integer.parseInt(r);
            } catch (NumberFormatException e) {
                System.out.println("invalid input, please enter a valid integer!");
            }

            if (x >= MAX || x < MIN) {
                System.out.println("invalid input, please enter a valid integer!");
            }

        }
        return x;
    }

    private TriantaEna(
            TEPlayer[] tePlayers
    ) {
        super(tePlayers);
    }
    static public TriantaEna getInstance() {
        if (INSTANCE == null) {
            int playersNumb = inquirePlayers();
            TEPlayer[] tePlayers = new TEPlayer[playersNumb];
            INSTANCE = new TriantaEna(tePlayers);
        }
        return INSTANCE;
    }

    @Override
    void startGame() {
        System.out.println("TriantaEna starts");
    }

    @Override
    void resetGame() {

    }
}
