package sample;

public class Score {
    private int score;
    private int level;

    public int getScore() {
        return score;
    }

    public void addToScore(int amount){
        score += amount;
    }

    public void addToScore(){
        addToScore(1);
    }
}
