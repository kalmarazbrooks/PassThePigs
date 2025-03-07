import java.util.ArrayList;

public class PassThePigs {

    static ArrayList<Player> players = new ArrayList<Player>();
    static ArrayList<Integer> scores = new ArrayList<Integer>();

    static int[][] scoreMatrix = {{1, 1}, {1, 1}, {5, 20}, {5, 20}, {10, 40},{15, 60}} ;

    static int winningScore = 100;
    static boolean playing = true;
    
    public static void main(String[] args) {
        System.out.println("Hello World!");

        while (playing) {
            int handScore = 0;

            for (int i = 0; i < players.size(); i++) {
                ArrayList<Integer> otherScores = new ArrayList<Integer>();

                for (int s = 0; s < scores.size(); s++) {
                    if (s != i) {
                        otherScores.add(scores.get(s));
                    }
                }

                if (players.get(i).wantsToRoll(scores.get(i), handScore, otherScores, winningScore)) {
                    int firstRoll = roll();
                    int secondRoll = roll();

                    if (firstRoll == secondRoll) {
                        handScore += scoreMatrix[firstRoll][2];
                        i--; // gives another chance to player
                    } else if ((firstRoll == 1 && secondRoll == 2) || (firstRoll == 2 && secondRoll == 1)) {
                        handScore = 0;
                        System.out.println("Pig Out!");
                    } else {
                        if (firstRoll > secondRoll) {
                            handScore += scoreMatrix[firstRoll][0];
                        } else {
                            handScore += scoreMatrix[secondRoll][0];
                        }
                        i--; // gives another chance to player
                    }
                } else {
                    scores.set(i, scores.get(i) + handScore);
                    handScore = 0; // resets handscore for next person
                }
            }

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