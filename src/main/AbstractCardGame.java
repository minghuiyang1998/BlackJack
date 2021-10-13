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
    
    private PlayerActionType chooseAction(PlayerActionType[] actions) {
        System.out.println("Please select a action id: ");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + ": " + actions[i].getName());
        }

        int id = -1;
        while (id > actions.length || id < 0) {
            String actionID = scanner.nextLine();
            try {
                id = Integer.parseInt(actionID);
            } catch (NumberFormatException e) {
                System.out.println("invalid input, please enter an integer!");
            }

            if (id > actions.length || id < 0) {
                System.out.println("invalid input, please enter a valid integer!");
            }
        }
        return actions[id];
    }

    abstract void startGame();

    public AbstractPlayer[] getPlayers() {
        return players;
    }

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
