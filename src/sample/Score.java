package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Score {
    private int score=0;
    private int level;
    private Label lbScore;
    private boolean isLabel;

    public Score(){}

    public Score(Label labelSource) {
        lbScore = labelSource;
        isLabel = true;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int amount){
        score += amount;
        if(isLabel){
            lbScore.setText(Integer.toString(score));
            System.out.println("Score:"+Integer.toString(score));
        }
    }

    public void addToScore(){
        addToScore(1);
    }
}
