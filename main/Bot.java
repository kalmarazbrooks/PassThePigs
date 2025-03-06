import java.util.ArrayList;

class Bot extends Player {
    
    public Bot(String name) {
        super(name);
    }
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        return true;
    }

    public void setStrategy(String strat) {
        super.setStrategy(strat);
    }
}