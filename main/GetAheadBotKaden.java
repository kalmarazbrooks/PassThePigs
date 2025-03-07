
import java.util.ArrayList;

class GetAheadBotKaden extends Bot {

    public GetAheadBotKaden(String name) {
        super(name);
        super.setStrategy("This bot just tries to get ahead!");
    }

    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        if (handScore < 5) {
            return true;
        } else if (handScore > 20) {
            return false;
        }

        for (Integer score : otherScores) {
            if (score > myScore) {
                return true;
            }
        }

        return false;
    }
}
