package main;

import java.util.ArrayList;
import java.util.Scanner;

abstract class AbstractCardGame {
    private final Scanner scanner;
    private boolean isEnd;

    private final AbstractPlayer[] players;

    AbstractCardGame(
            AbstractPlayer[] players
    ) {
        this.players = players;
        this.isEnd = false;
        this.scanner = new Scanner(System.in);
    }

    private boolean inquireIsExit() {
        System.out.println("Whether to restart the game? (y/n)");
        String answer = scanner.nextLine();
        while (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
            answer = scanner.nextLine();
            if (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
                System.out.println("invalid input, please enter a valid input!");
            }
        }

        boolean isExit = false;
        if (answer.equals("y") ) {
            isExit = true;
        }
        return isExit;
    }

    abstract void startGame();

    void start() {
          this.startGame();
//        while (!isEnd) {
//            this.startGame();
//            if (inquireIsExit()) {
//                resetGame();
//            } else {
//                isEnd = true;
//            }
//        }
    }
}
