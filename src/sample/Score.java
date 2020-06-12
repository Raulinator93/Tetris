package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Score extends Label{
    private int score=0;
    private int level;
    private boolean isLabel;

    public int getScore() {
        return score;
    }

    public void addToScore(int amount){
        score += amount;
        setText(Integer.toString(score));
        System.out.println("Score:"+Integer.toString(score));
    }

    public void addToScore(){
        addToScore(1);
    }

    public void getScoreRemovedLines(int lines){
        int pointsPerLine;
        switch (lines){
            case 1:
                pointsPerLine=40;
                break;
            case 2:
                pointsPerLine=100;
                break;
            case 3:
                pointsPerLine=300;
                break;
            case 4:
                pointsPerLine=1200;
                break;
            default:
                pointsPerLine=0;
        }
        addToScore(pointsPerLine*(level+1));
    }
    public void setScoreToLabel(){
        setText(Integer.toString(score));
    }
}
