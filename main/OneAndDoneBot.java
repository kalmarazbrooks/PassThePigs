import java.util.ArrayList;

class OneAndDoneBot extends Bot {

    public OneAndDoneBot(String name) {
        super(name);
        super.setStrategy("One step at a time...");
    }
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        if (handScore == 0) {
            return true;
        }

        return false;
    }
}
