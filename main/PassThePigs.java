
import java.util.ArrayList;

public class PassThePigs {

    static ArrayList<Player> players = new ArrayList<Player>();
    static ArrayList<Integer> scores = new ArrayList<Integer>();

    static Object[][] scoreMatrix = {{1, 1, "Dot!"}, {1, 1, "No Dot!"}, {5, 20, "Razorback!"}, {5, 20, "Trotter!"}, {10, 40, "Snouter!"}, {15, 60, "Leaning Jowler!"}};

    static int winningScore = 100;
    static boolean playing = true;

    public static void main(String[] args) {
        setup();

        while (playing) {
            int handScore = 0; // current hand score for current player's turn

            for (int i = 0; i < players.size(); i++) {
                // pre-round

                announceScores();

                ArrayList<Integer> otherScores = new ArrayList<Integer>();
                populateOtherScores(otherScores, i);

                playing = checkWinCondition(handScore + scores.get(i));

                if (!playing) {
                    scores.set(i, scores.get(i) + handScore);
                    break;
                }

                if (handScore == 0) {
                    System.out.println();
                    System.out.println(players.get(i).getName() + ", You're Up!");
                }

                // round

                if (players.get(i).wantsToRoll(scores.get(i), handScore, otherScores, winningScore)) {
                    int roundScore = rollPigs();

                    if (roundScore != -1) {
                        i--; // gives another chance to player
                        handScore += roundScore;
                    } else {
                        handScore = 0;
                    }

                } else {
                    scores.set(i, scores.get(i) + handScore);
                    handScore = 0; // resets handscore for next person
                }
            }

        }

        goodGame();
    }

    public static void setup() {
        players.add(new Human("Me"));
        players.add(new Human("You"));

        for (Player player : players) {
            scores.add(0);
        }
    }

    public static void announceScores() {
        System.out.println();
        for (int i = 0; i < players.size(); i++) {
            System.out.print(players.get(i).getName() + "'s Score: " + scores.get(i) + " | ");
        }
        System.out.println();
    }

    public static void populateOtherScores(ArrayList<Integer> otherScores, int playerIndex) {
        for (int s = 0; s < scores.size(); s++) {
            if (s != playerIndex) {
                otherScores.add(scores.get(s));
            }
        }
    }

    public static int rollPigs() {
        int firstRoll = roll();
        int secondRoll = roll();

        int score = 0;
        System.out.println(); // creates a space for new stats.

        if (firstRoll == secondRoll) {
            score += (int) scoreMatrix[firstRoll][1];
            System.out.println("Wow, a double " + scoreMatrix[firstRoll][2]);
        } else if ((firstRoll == 0 && secondRoll == 1) || (firstRoll == 1 && secondRoll == 0)) {
            score = -1;
            System.out.println("Pig Out!");
        } else {
            if (firstRoll > secondRoll) {
                score += (int) scoreMatrix[firstRoll][0];
                System.out.println(scoreMatrix[firstRoll][2]);
            } else {
                score += (int) scoreMatrix[secondRoll][0];
                System.out.println(scoreMatrix[secondRoll][2]);
            }
        }

        return score;
    }
    public static boolean checkWinCondition(int score) {
        if (score >= winningScore) {
            return false;
        }

        return true;
    }

    public static void goodGame(){
        System.out.println();
        System.out.println("Great job to all the players!");

        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).name + "'s Score: " + scores.get(i));
        }
    }

    public static int roll() {
        int type;
        double roll = Math.random() * 100;

        if (roll < 34.9) {
            type = 0;
        } else if (roll < 65.1) {
            type = 1;
        } else if (roll < 87.5) {
            type = 2;
        } else if (roll < 96.3) {
            type = 3;
        } else if (roll < 99.3) {
            type = 4;
        } else {
            type = 5;
        }

        return type;
    }

}
