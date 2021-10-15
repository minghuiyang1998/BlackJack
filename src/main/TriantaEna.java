package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            // TODO: init dealer with the player who has highest balance
            // Sort tePlayers in descending order
            Collections.sort(tePlayers);
            TEDealer teDealer = new TEDealer(tePlayers.get(0).getName(), tePlayers.get(0).getBalance(), poker);
            tePlayers.remove(0);
            TEReferee teReferee = new TEReferee();
            INSTANCE = new TriantaEna(tePlayers, teDealer, teReferee);
        }
        return INSTANCE;
    }

    public Money inquireBet(int balance) {
        System.out.println("Please negotiate on bet amount(at least 1):");
        Scanner scanner = new Scanner(System.in);

        int val = 0;
        final int MIN_VAL = 1;
        while (val < MIN_VAL || val > balance || val > teDealer.bank.getValue()/tePlayers.size()) {
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

    boolean inquireNewBanker(int playerIndex) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tePlayers.get(playerIndex).getName() + ", do you want to be the banker? (y/n)");
        String answer = scanner.nextLine();
        while (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
            answer = scanner.nextLine();
            if (answer.length() != 1 || !(answer.equals("y") || answer.equals("n"))) {
                System.out.println("invalid input, please enter a valid input!");
            }
        }
        boolean isWilling = false;
        if (answer.equals("y") ) {
            isWilling = true;
        }
        return isWilling;
    }

    void switchDealer(int playerIndex) {
        // TODO:  find the player with specified conditions:
        //  when the player has greater amount of money than current banker
        TEPlayer player = tePlayers.get(playerIndex);
        // dealer is actually initialized here if there is some player becomes a dealer
        TEPlayer formerDealer = new TEPlayer(teDealer.getName(), teDealer.getBank());
        teDealer = new TEDealer(player.getName(), player.getBalance(), new Poker());
        tePlayers.add(playerIndex, formerDealer);
        // TODO: check whether remove index is correct
        tePlayers.remove(playerIndex + 1);

    }

    private void playAction(TEPlayer p) {
        System.out.print(p.getName() + ", ");
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
        System.out.println("Dealer: " + teDealer.getName() + "(Balance: " + teDealer.getBank() + ")");
        for (TEPlayer p: tePlayers) {
            System.out.print(p.getName() + "(Balance: " + p.getBalance() + ") " );
        }
        System.out.println();

        int minBalance = tePlayers.get(tePlayers.size() - 1).getBalance(); // min balance amount of all current players
        Money bet = inquireBet(minBalance);
        // Dealer set bet
        Money dealerBet = new Money(bet.getValue() * tePlayers.size());
        teDealer.setBank(teDealer.getBank() - bet.getValue());
        teDealer.setBet(dealerBet);
        System.out.println("Dealer: " + teDealer.getName() + "(Balance: " + teDealer.getBank() + ")");
        // Player set bet
        for (TEPlayer p: tePlayers) {
            p.setBalance(p.getBalance() - bet.getValue());
            p.setBet(bet);
            System.out.print(p.getName() + "(Balance: " + p.getBalance() + ") " );
        }
        System.out.println();

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
            boolean isExceed = teReferee.isExceed(teDealer.getHand(), EXCEED_VAL);
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
                boolean dealerWin = true;
                System.out.println("All stand and exceed dealer players win!");
                int dealerVal = teReferee.getHandValue(teDealer.getHand());
                for (TEPlayer p: tePlayers) {
                    int win = 0;
                    Hand h = p.getHand();
                    if (h.isStand() && teReferee.getHandValue(h) > dealerVal) {
                        win = h.getBet().getValue() * 2;
                        dealerWin = false;
                    }
                    p.setBalance(p.getBalance() + win);
                }
                if (dealerWin) {
                    teDealer.setBank(teDealer.getBet().getValue() * 2);
                }
            }
            isRoundEnd = true;
        }

    }

    @Override
    void resetGame() {
        for (TEPlayer p : tePlayers) {
            p.reset();
        }
        teDealer.reset();
        // TODO: see if any player's balance exceed banker
        // let player to decide whether to be a banker
        Collections.sort(tePlayers);
        for (int i = 0; i < tePlayers.size(); i++) {
            if (tePlayers.get(i).getBalance() <= teDealer.getBank()) {
                break;
            }
            if(inquireNewBanker(i)) {
                switchDealer(i);
                break;
            }
        }
        Collections.sort(tePlayers);
    }
}
