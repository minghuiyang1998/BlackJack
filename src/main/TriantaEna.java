package main;

import java.util.ArrayList;
import java.util.Scanner;

class TriantaEna extends AbstractCardGame {
    private static TriantaEna INSTANCE = null;
    private final ArrayList<TEPlayer> tePlayers;
    private final TEDealer teDealer;
    private final TEReferee teReferee;

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
            ArrayList<TEPlayer> tePlayers,
            TEDealer teDealer,
            TEReferee teReferee
    ) {
        super();
        this.tePlayers = tePlayers;
        this.teDealer = teDealer;
        this.teReferee = teReferee;
    }
    static public TriantaEna getInstance() {
        if (INSTANCE == null) {
            int playersNumb = inquirePlayers();
            ArrayList<TEPlayer> tePlayers = new ArrayList<>();
            for (int i = 0; i < playersNumb; i++) {
                tePlayers.add(new TEPlayer("TEPlayer " + i, 100));
            }
            Poker poker = new Poker();
            TEDealer teDealer = new TEDealer(poker);
            TEReferee teReferee = new TEReferee();
            INSTANCE = new TriantaEna(tePlayers, teDealer, teReferee);
        }
        return INSTANCE;
    }

    @Override
    void startGame() {
        boolean isRoundEnd = false;
        while (!isRoundEnd) {
            // referee decide isRoundEnd
            //TODO: get player status to decide player's actionlist
            ArrayList<PlayerActionType> actions = new ArrayList<>();
            PlayerActionType a = chooseAction(actions);
            //TODO: dealer.react(a)
            //TODO: referee

        }
        /**
         * for all players currIndex = -1 -> dealer
         * while (!isExceeded) { //17
         *    dealer.addCard()
         * }
         *
         * isDealerBust = referee.isBust(dealer hand) //dealer
         * if (isDealerBust) {
         *    all players' hands win
         *    (TE记得给钱）
         * } else {
         *
         *
         * }
         *
         *
         *
         */

    }

    @Override
    void resetGame() {

    }
}
