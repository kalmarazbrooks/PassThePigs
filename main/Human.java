
import java.util.ArrayList;
import java.util.Scanner;

class Human extends Player {

    Scanner sc = new Scanner(System.in);

    public Human(String name) {
        super(name);
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        System.out.println();
        System.out.println("Your Total Score: " + myScore);
        System.out.println("Your Current Hand Score: " + handScore);
        System.out.println("Score to Win: " + (winningScore - myScore));
        System.out.println();
        System.out.println("Would you like to roll?   y/n");

        String answer = sc.nextLine();

        if (answer.equals("y")) {
            return true;
        }

        return false;
    }
}
