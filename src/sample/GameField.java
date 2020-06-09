package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameField extends GridPane {
    public void createGameField(int x, int y){
        this.setGridLinesVisible(true);
        this.setHgap(1);
        this.setVgap(1);
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                Label label = new Label();
                label.setMinSize(30,30);
                this.add(label, i, j);
            }
        }
    }
}
