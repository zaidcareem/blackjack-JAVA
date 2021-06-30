import java.util.*;

public class blackJack {

    public static void main(String[] args) {
        String[] heartList = {"H_ACE", "H_2", "H_3", "H_4", "H_5", "H_6", "H_7", "H_8", "H_9", "H_10", "H_J", "H_Q", "H_K"};
        String[] spadeList = {"S_ACE", "S_2", "S_3", "S_4", "S_5", "S_6", "S_7", "S_8", "S_9", "S_10", "S_J", "S_Q", "S_K"};
        String[] diamondList = {"D_ACE", "D_2", "D_3", "D_4", "D_5", "D_6", "D_7", "D_8", "D_9", "D_10", "D_J", "D_Q", "D_K"};
        String[] clubList = {"C_ACE", "C_2", "C_3", "C_4", "C_5", "C_6", "C_7", "C_8", "C_9", "C_10", "C_J", "C_Q", "C_K"};
        ArrayList<String> cardPack = new ArrayList<String>();
// form cards
        for (String s : heartList) {
            cardPack.add(s);
        }
        for (String s : spadeList) {
            cardPack.add(s);
        }
        for (String s : diamondList) {
            cardPack.add(s);
        }
        for (String s : clubList) {
            cardPack.add(s);
        }

        HashMap<String, Integer> h = new HashMap<String,Integer>();
        HashMap<String, Integer> s = new HashMap<String,Integer>();
        HashMap<String, Integer> d = new HashMap<String,Integer>();
        HashMap<String, Integer> c = new HashMap<String,Integer>();
// form points
        for (int i = 0; i < 10; i++) {
            h.put(heartList[i], (i + 1));
        }
        for (int e = 10; e < 13; e++) {
            h.put(heartList[e], 10);
        }
        for (int i = 0; i < 10; i++) {
            s.put(spadeList[i], (i +1));
        }
        for (int e = 10; e < 13; e++) {
            h.put(spadeList[e], 10);
        }
        for (int i = 0; i < 10; i++) {
            d.put(diamondList[i], (i + 1));
        }
        for (int e = 10; e < 13; e++) {
            h.put(diamondList[e], 10);
        }
        for (int i = 0; i < 10; i++) {
            c.put(clubList[i], (i +1));
        }
        for (int e = 10; e < 13; e++) {
            h.put(clubList[e], 10);
        }

        HashMap<String, Integer> cardPoints = new HashMap<String, Integer>();
        cardPoints.putAll(h);
        cardPoints.putAll(s);
        cardPoints.putAll(d);
        cardPoints.putAll(c);

        // start play
        Scanner in = new Scanner(System.in);
        Collections.shuffle(cardPack); // form deck
        int roundNumber = 1;
        System.out.println("\nWELCOME TO BLACK JACK\n");
        System.out.print("Enter your amount: ");
        double Money = in.nextDouble();
        ArrayList<String> myCards = new ArrayList<String>();
        ArrayList<String> dealersCards = new ArrayList<String>();
        int myScore = 0;
        int dealersScore = 0;
// loop start
        while (cardPack.size() > 3) {

            if (Money <= 0) {
                System.out.println("\nGAME OVER! YOU ARE BANKRUPT!\n");
                break;
            }

            if (cardPack.size() < 5) {
                System.out.println("                                        No:of cards remaining: " + cardPack.size());
            }
            System.out.println("\n                      ------------------------------- Round ------------------------------ " + roundNumber + "\n");
            roundNumber += 1;
            System.out.print("Enter your bet: ");
            double Bet = in.nextDouble();

            while (Bet > Money) {
                System.out.println("Bet should be less than or equal to your amount\n");
                System.out.print("Enter your bet: ");
                Bet = in.nextDouble();
            }

            Money -= Bet;
            System.out.println("Money at hand: $ " + Money);


            boolean myFirstCard = myCards.add(cardPack.get(0));
            int myFirstValue = cardPoints.get(cardPack.get(0));
            cardPack.remove(0);
            boolean dealersFirstCard = dealersCards.add(cardPack.get(0));
            int dealersFirstValue = cardPoints.get(cardPack.get(0));
            cardPack.remove(0);
            boolean mySecondCard = myCards.add(cardPack.get(0));
            int mySecondValue = cardPoints.get(cardPack.get(0));
            cardPack.remove(0);

            dealersScore = dealersFirstValue;

            System.out.print("\nYour cards: ");
            System.out.println(myCards);
            if (myFirstValue == 1) {
                System.out.println("What value would you like your ACE to be: 1 or 11? ");
                int choiceOneAce = in.nextInt();
                myFirstValue = choiceOneAce;
            }else if (mySecondValue == 1) {
                System.out.println("What value would you like your ACE to be: 1 or 11? ");
                int choiceTwoAce = in.nextInt();
                mySecondValue = choiceTwoAce;
            }

            myScore = myFirstValue + mySecondValue;

            System.out.println("Your Score: " + myScore);
            System.out.print("\nDealers cards: ");
            System.out.println(dealersCards);
            System.out.println("Dealers partial Score: " + dealersScore);


            System.out.print("\nEnter \"h\" if you want to hit or anything else to stand: ");
            Scanner one = new Scanner(System.in);
            String choiceOne = one.nextLine();

            while (choiceOne.equals("h")) {

                boolean myThirdCard = myCards.add(cardPack.get(0));
                System.out.print("\nYour cards(updated): ");
                System.out.println(myCards);
                int myThirdValue = cardPoints.get(cardPack.get(0));
                if (myThirdValue == 1) {
                    System.out.print("Choose a value for your ACE: 1 OR 11? ");
                    myThirdValue = in.nextInt();
                }
                myScore += myThirdValue;
                cardPack.remove(0);
                System.out.print("\nYour Score: ");
                System.out.println(myScore);
                if (myScore > 21) {
                    break;
                }
                System.out.print("\nDo you want to hit again? y/n: ");
                Scanner two = new Scanner(System.in);
                String choiceTwo = two.nextLine();
                if (choiceTwo.equals("n")) {
                    break;
                }
            }
            boolean dealersSecondCard = dealersCards.add(cardPack.get(0));
            System.out.print("Dealers cards: ");
            System.out.println(dealersCards);
            int dealersSecondValue = cardPoints.get(cardPack.get(0));
            cardPack.remove(0);
            if ((dealersFirstValue == 1) && (dealersSecondValue != 1)) {
                dealersFirstValue = 11;
            }
            else if ((dealersSecondValue == 1) && (dealersFirstValue != 1)) {
                dealersSecondValue = 11;
            }
            dealersScore = dealersFirstValue + dealersSecondValue;
            System.out.println("Dealers score: " + dealersScore);
            myCards.clear();

            if (myScore > 21) {
                System.out.println("\n                                          YOU ARE BUST!\n");
                System.out.println("\nComputer wins the round");
            } else if (myScore == 21) {
                System.out.println(("\nYOU GOT BLACK JACK!"));
                Money += (2.5 * Bet);
            } else if ((myScore >= dealersScore)) {
                System.out.println("\nPlayer wins the round!");
                Money += (2 * Bet);
            } else {
                System.out.println("\nComputer wins the round");
            }

            dealersCards.clear();
            System.out.println("\nMoney at rounds end: $ " + Money);
        }
    }
}