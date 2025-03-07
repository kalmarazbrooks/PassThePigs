
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
            game();
        }

        goodGame(); // When game ends, this plays.
    }

    public static void setup() { // Hard Code new bots + players
        players.add(new GetAheadBotKaden("GetAhead"));
        players.add(new RiskItAllBotKaden("RiskIt"));
        players.add(new OneAndDoneBotKaden("OneAndDone"));
        players.add(new Human("You"));

        for (Player player : players) {
            scores.add(0);
        }

        System.out.println("Let's play Pass The Pigs!");
    }

    public static void game() {
        int handScore = 0; // current hand score for current player's turn

        for (int i = 0; i < players.size(); i++) {
            // pre-round

            announceScores(handScore);

            ArrayList<Integer> otherScores = new ArrayList<Integer>();
            if (!preRound(handScore, otherScores, i)) {
                break; // breaks the for loop to return and end the game loop.
            }

            // round
            int iterationScore = round(handScore, i, otherScores);

            if (iterationScore > 0) {
                handScore = iterationScore;
                i--;
            } else {
                handScore = 0;
            }
        }
    }

    public static boolean preRound(int handScore, ArrayList<Integer> otherScores, int iteration) {
        populateOtherScores(otherScores, iteration);

        playing = checkWinCondition(handScore + scores.get(iteration));

        if (!playing) {
            scores.set(iteration, scores.get(iteration) + handScore);
            return false;
        }

        if (handScore == 0) {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println(players.get(iteration).getName() + ", You're Up!");
        }

        return true;
    }

    public static int round(int handScore, int iteration, ArrayList<Integer> otherScores) {
        if (players.get(iteration).wantsToRoll(scores.get(iteration), handScore, otherScores, winningScore)) {

            int roundScore = rollPigs();

            if (roundScore != -1) {
                handScore += roundScore;
            } else {
                handScore = 0;
            }

        } else {
            scores.set(iteration, scores.get(iteration) + handScore);
            handScore = 0; // resets handscore for next person
            System.out.println();
            System.out.println(players.get(iteration).getName() + " passes!");
        }

        return handScore;
    }

    public static void announceScores(int handScore) {
        System.out.println();
        for (int i = 0; i < players.size(); i++) {
            System.out.print(players.get(i).getName() + "'s Score: " + scores.get(i) + " | ");
        }
        System.out.println("Hand Score: " + handScore);
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

        if (firstRoll == secondRoll) { // Double!
            score += (int) scoreMatrix[firstRoll][1];
            System.out.println("Wow, a double " + scoreMatrix[firstRoll][2]);
        } else if ((firstRoll == 0 && secondRoll == 1) || (firstRoll == 1 && secondRoll == 0)) { // Uh oh! Pig Out!
            score = -1;
            System.out.println("Pig Out!");
        } else {
            if (firstRoll > secondRoll) { // First pig scores better than second pig
                score += (int) scoreMatrix[firstRoll][0];
                System.out.println(scoreMatrix[firstRoll][2]);
            } else { // Second pig scores better than first pig
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

    public static void goodGame() {
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        System.out.println("Great job to all the players!");

        for (int i = 0; i < players.size(); i++) {
            System.out.println();
            System.out.println(players.get(i).name + "'s Score: " + scores.get(i));
        }
    }

    public static int roll() { // See scoreMatrix for more information on points.
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
