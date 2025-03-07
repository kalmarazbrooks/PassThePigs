import java.util.ArrayList;

class BotKaden extends Player {
    
    public BotKaden(String name) {
        super(name);
    }
    public boolean wantsToRoll(int myScore, int handScore, ArrayList<Integer> otherScores, int winningScore) {
        return true;
    }

    public void setStrategy(String strat) {
        super.setStrategy(strat);
    }
}