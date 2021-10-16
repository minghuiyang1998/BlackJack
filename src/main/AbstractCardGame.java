package main;

import java.util.ArrayList;
import java.util.Scanner;

abstract class AbstractCardGame {
    private final Scanner scanner;
    private boolean isEnd;

    AbstractCardGame() {
        this.isEnd = false;
        this.scanner = new Scanner(System.in);
    }

    public Money inquireBet(int balance) {
        System.out.println("Input the bet(at least 1): ");
        int val = 0;
        final int MIN_VAL = 1;
        while (val < MIN_VAL || val > balance) {
            String betVal = scanner.nextLine();
            try {
                val = Integer.parseInt(betVal);
            } catch (NumberFormatException e) {
                System.out.println("invalid input, please enter an integer!");
            }

            if (val < MIN_VAL || val > balance) {
                System.out.println("invalid input, please enter a valid integer!");
            }
        }
        return new Money(val);
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
    
    PlayerActionType chooseAction(ArrayList<PlayerActionType> actions) {
        System.out.println("Please select an action id: ");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ": " + actions.get(i).getName());
        }

        int id = -1;
        while (id > actions.size() || id < 0) {
            String actionID = scanner.nextLine();
            try {
                id = Integer.parseInt(actionID);
            } catch (NumberFormatException e) {
                System.out.println("invalid input, please enter an integer!");
            }

            if (id > actions.size() || id < 0) {
                System.out.println("invalid input, please enter a valid integer!");
            }
        }
        return actions.get(id);
    }

    abstract void startGame();
    abstract void resetGame();

    void start() {
        while (!isEnd) {
            startGame();
            if (inquireIsExit()) {
                resetGame();
            } else {
                isEnd = true;
            }
        }
    }

    int addNewPlayer(int max_val) {
        System.out.println("Add new players? (y/n)");
        String answer = scanner.nextLine();
        while (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
            answer = scanner.nextLine();
            if (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
                System.out.println("invalid input, please enter a valid input!");
            }
        }
        if (answer.equals("n") ) {
            return 0;
        }
        else {
            int num = -1;
            while (num > max_val || num < 0) {
                String playerNum = scanner.nextLine();
                try {
                    num = Integer.parseInt(playerNum);
                } catch (NumberFormatException e) {
                    System.out.println("invalid input, please enter an integer!");
                }

                if (num > max_val || num < 0) {
                    System.out.println("invalid input, please enter a valid integer!");
                }
            }
            return num;
        }
    }

}
