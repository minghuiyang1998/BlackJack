package main;

import java.util.Scanner;

class MainGame {
    private final Scanner scanner;

    MainGame() {
        this.scanner = new Scanner(System.in);
    }

    private CardGameType chooseGame() {
        System.out.println("Please select a game id: ");
        CardGameType[] types = CardGameType.values();
        for (CardGameType t : types) {
            System.out.println(t.getId() + ": " + t.getName());
        }

        int id = -1;
        while (id > types.length || id < 0) {
            String gameID = scanner.nextLine();
            try {
                id = Integer.parseInt(gameID);
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("invalid input, please enter an integer!");
            }

            if (id > types.length || id < 0) {
                System.out.println("invalid input, please enter a valid integer!");
            }
        }

        return types[id];
    }

    void startGame() {
        CardGameType type = chooseGame();
        AbstractCardGame game = type.getGame();
        game.start();
    }


}
