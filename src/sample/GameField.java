package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.Shapes.Shape;

public class GameField extends GridPane {
    private Label[][] gameField;

    public GameField() {
        setGridLinesVisible(true);
        setHgap(1);
        setVgap(1);
    }

    public void createGameField(int x, int y) {
        gameField = new Label[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Label label = new Label();
                label.setMinSize(30, 30);
                label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                gameField[i][j] = label;
                add(label, i, j);
            }
        }
    }

    public boolean isCollapsing(Shape shape, int x, int y) {
        if (y >= gameField[0].length || x < 0 || x + shape.getWidth() > gameField.length) {
            return true;
        }
        for (int i = 0; i < shape.getFilledRects().length; i++) {
            for (int j = 0; j < shape.getFilledRects()[0].length; j++) {
                if (shape.getFilledRects()[i][j] && y - j >= 0) {
                    if (gameField[x + i][y - j].getBackground().getFills().get(0).getFill() != Color.WHITE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void drawShape(Shape shape, int x, int y) {
        drawShapeBottomBorder(shape, x, y);
        for (int i = 0; i < shape.getFilledRects().length; i++) {
            for (int j = 0; j < shape.getFilledRects()[0].length; j++) {
                if (shape.getFilledRects()[i][j] && y - j >= 0) {
                    gameField[x + i][y - j].setBackground(new Background(new BackgroundFill(shape.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }

    }

    public void deleteShape(Shape shape, int x, int y) {
        deleteAllBorders();
        for (int i = 0; i < shape.getFilledRects().length; i++) {
            for (int j = 0; j < shape.getFilledRects()[0].length; j++) {
                if (shape.getFilledRects()[i][j] && y - j >= 0) {
                    gameField[x + i][y - j].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        }
    }

    public void drawShapeBottomBorder(Shape shape, int x, int y) {
        int k;
        for (k = 0; !isCollapsing(shape, x, y + k); k++) {
        }
        k--;
        y = y + k;
        for (int i = 0; i < shape.getFilledRects().length; i++) {
            for (int j = 0; j < shape.getFilledRects()[0].length; j++) {
                if (shape.getFilledRects()[i][j] && y - j >= 0) {
                    gameField[x + i][y - j].setStyle("-fx-border-width: 2;-fx-border-color:" + shape.getColor().toString().replace("0x", "#").replace("ff", ""));
                }
            }
        }
    }

    public void deleteAllBorders() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                gameField[i][j].setStyle("");
            }
        }
    }

    public void removeFullLines() {
        for (int j = 0; j < gameField[0].length; j++) {
            boolean isEmpty = false;
            for (int i = 0; i < gameField.length && !isEmpty; i++) {
                isEmpty = gameField[i][j].getBackground().getFills().get(0).getFill() == Color.WHITE;
            }
            if (isEmpty == false) {
                for (; j > 0; j--) {
                    for (int i = 0; i < gameField.length && !isEmpty; i++) {
                        gameField[i][j].setBackground(gameField[i][j - 1].getBackground());
                    }
                }
            }
        }
    }
}
