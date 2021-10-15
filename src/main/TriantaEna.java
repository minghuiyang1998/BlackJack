package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static main.PlayerActionType.*;

class TriantaEna extends AbstractCardGame {
    private static TriantaEna INSTANCE = null;
    private ArrayList<TEPlayer> tePlayers;
    private TEDealer teDealer;
    private final TEReferee teReferee;
    private PlayerActionType[] actionArray = {HIT, STAND, DEAL};
    private final ArrayList<PlayerActionType> actions = new ArrayList<>(Arrays.asList(actionArray));

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
            TEDealer teDealer = new TEDealer(poker, );
            TEReferee teReferee = new TEReferee();
            INSTANCE = new TriantaEna(tePlayers, teDealer, teReferee);
        }
        return INSTANCE;
    }

    void switchDealer(int playerIndex) {
        // TODO:  find the player with specified conditions:
        //  when the player has greater amount of money than current banker
        TEPlayer player = tePlayers.get(playerIndex);
        // dealer is actually initialized here if there is some player becomes a dealer
        TEPlayer formerDealer = new TEPlayer(teDealer.getName(), teDealer.getBank().getValue());
        teDealer = new TEDealer(player.getName(), player.getBalance(), new Poker());
        tePlayers.add(playerIndex, formerDealer);
        // TODO: check whether remove index is correct
        tePlayers.remove(playerIndex + 1);

    }

    private void playAction(TEPlayer p) {
        PlayerActionType a = chooseAction(this.actions);
        switch (a) {
            case HIT:
                Card card = teDealer.getRandomCard();
                p.hit(card);
                break;
            case STAND:
                p.standCurr();
                break;
            case DEAL:
                ArrayList<Card> playerCards = teDealer.deal(2);
                p.deal(playerCards);
                // dealer add cards
                ArrayList<Card> dealerCards = teDealer.deal(2);
                int DEALER_CARDS_LEN = dealerCards.size();
                for (int i = 0; i < DEALER_CARDS_LEN; i++) {
                    Card c = dealerCards.get(i);
                    if (i == DEALER_CARDS_LEN - 1) {
                        c.setShown(false);
                    }
                    teDealer.addCard(c);
                }
                break;
            default:
                break;
        }
    }

    private void printTable(){
        // TODO: printTable
        System.out.println("Banker:");
        ArrayList<Card> dealerCards = teDealer.getHand().getDeck();
        for (Card c: dealerCards) {
            if (c.isShown()) {
                System.out.print(c.getSuit() + c.getName()+ " ");
            }
        }
        System.out.println();

        for (TEPlayer p: tePlayers) {
            System.out.println(p.getName() + " " + "Balance:" + p.getBalance() + " ");
            Hand hand = p.getHand();
            System.out.print("Bet: " + hand.getBet().getValue() + " Cards: ");
            ArrayList<Card> pCards = hand.getDeck();
            for (Card c: pCards) {
                if (c.isShown()) {
                    System.out.print(c.getSuit() + c.getName() + " ");
                }
            }
            if (hand.isBust()) {
                System.out.print("<Bust> ");
            }
            if (hand.isStand()) {
                System.out.print("<Stand> ");
            }
            System.out.println();

        }
    }

    @Override
    void startGame() {
        // TODO: can the player who has least amount of money be a banker?
        // TODO: keep inquiring bet if n * bet exceeds banker's bank?
        Money bet = new Money(0);
        while (teDealer.bank.compareTo(tePlayers.size() * bet.getValue())){
            bet = inquireBet();   // begin with same amount of bet
        }

        boolean isRoundEnd = false;
        while (!isRoundEnd) {
            // referee decide isRoundEnd
            for (TEPlayer p : tePlayers) {
                boolean isPlayerStop = teReferee.isPlayerStop(p);
                if (isPlayerStop) continue;
                playAction(p);

                // referee
                if (teReferee.isBust(p.getHand())) {
                    p.setCurrBust(true);
                }
                printTable();
            }

            boolean isAllPlayerStop = teReferee.isAllPlayersStop(tePlayers);
            if (!isAllPlayerStop) continue;

            // dealer
            int EXCEED_VAL = 27;
            boolean isExceed = teReferee.isExceed(teDealer.getHand(), EXCEED_VAL);;
            while (!isExceed) {
                Card c = teDealer.getRandomCard();
                teDealer.addCard(c);
                isExceed = teReferee.isExceed(teDealer.getHand(), EXCEED_VAL);
            }
            printTable();

            boolean isDealerBust = teReferee.isBust(teDealer.getHand());
            // TODO: calc money dealer have to send

            if (isDealerBust) {
                System.out.println("All stand players win!");
                for (TEPlayer p: tePlayers) {
                    Hand h = p.getHand();
                    int win = 0;
                    if (h.isStand()) {
                        win = h.getBet().getValue() * 2;
                    }
                    p.setBalance(p.getBalance() + win);
                    // TODO: update dealer bank

                }
            } else {
                System.out.println("All stand and exceed dealer players win!");
                int dealerVal = teReferee.getHandValue(teDealer.getHand());
                for (TEPlayer p: tePlayers) {
                    int win = 0;
                    Hand h = p.getHand();
                    if (h.isStand() && teReferee.getHandValue(h) > dealerVal) {
                        win = h.getBet().getValue() * 2;
                    }
                    p.setBalance(p.getBalance() + win);
                    // update dealer bank

                }
            }
            isRoundEnd = true;
        }
        // TODO: see if any player's balance exceed banker
        // let player to decide whether to be a banker

    }

    @Override
    void resetGame() {

    }
}
