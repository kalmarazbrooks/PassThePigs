import java.util.ArrayList;

class RiskItAllBot extends Bot {

    public RiskItAllBot(String name) {
        super(name);
        super.setStrategy("Like GetAheadBot, but it really does not stop until it gets ahead.");
    }
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        if (handScore < 15) {
            return true;
        }

        for (Integer score : otherScores) {
            if (score > handScore) {
                return true;
            }
        }

        return false;
    }
}