package sample;

import javafx.fxml.FXML;

public class Controller {
    @FXML
    GameField gpGameField;
    public void initialize(){
        gpGameField.createGameField(10,20);
        System.out.println("test");
    }
}
