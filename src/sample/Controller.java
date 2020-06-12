package sample;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Shapes.*;

import java.util.Random;
import java.util.concurrent.*;

public class Controller {
    @FXML GameField gpGameField;
    private ScheduledExecutorService scheduledExecutorService;
    private int yoffset;
    private int xoffset;
    private int cols = 10;
    private int rows = 20;
    private int timeDelay = 1000;
    private Shape shape;

    @FXML Score lbScore;

    public void initialize() {

        Runnable gameTick = () -> {
            gpGameField.deleteShape(shape, xoffset, yoffset);
            if (gpGameField.isCollapsing(shape, xoffset, yoffset + 1)) {
                gpGameField.drawShape(shape, xoffset, yoffset);
                shape = getRandomShape();
                yoffset = shape.getHeight() - 1;
                xoffset = (cols - shape.getWidth()) / 2;
                lbScore.getScoreRemovedLines(gpGameField.removeFullLines());
            } else {
                yoffset++;
            }
            if (gpGameField.isCollapsing(shape, xoffset, yoffset)) {
                System.out.println("verloren");
                scheduledExecutorService.shutdown();
            } else {
                gpGameField.drawShape(shape, xoffset, yoffset);
            }
        };

        gpGameField.createGameField(cols, rows);
        shape = getRandomShape();
        xoffset = (cols - shape.getWidth()) / 2;
        yoffset = shape.getHeight() - 1;
        gpGameField.drawShape(shape, xoffset, yoffset);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(gameTick, timeDelay, timeDelay, TimeUnit.MILLISECONDS);


        gpGameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                gpGameField.deleteShape(shape, xoffset, yoffset);
                if (!gpGameField.isCollapsing(shape, xoffset - 1, yoffset)) {
                    xoffset--;
                    gpGameField.drawShape(shape, xoffset, yoffset);
                } else {
                    gpGameField.drawShape(shape, xoffset, yoffset);
                }
            } else if (event.getCode() == KeyCode.RIGHT) {
                gpGameField.deleteShape(shape, xoffset, yoffset);
                if (!gpGameField.isCollapsing(shape, xoffset + 1, yoffset)) {
                    xoffset++;
                    gpGameField.drawShape(shape, xoffset, yoffset);
                } else {
                    gpGameField.drawShape(shape, xoffset, yoffset);
                }
            } else if (event.getCode() == KeyCode.UP) {
                gpGameField.deleteShape(shape, xoffset, yoffset);
                Shape tempshape = shape.clone();
                tempshape.rotate();
                if (!gpGameField.isCollapsing(tempshape, xoffset, yoffset)) {
                    shape = tempshape;
                }
                gpGameField.drawShape(shape, xoffset, yoffset);
            } else if (event.getCode() == KeyCode.DOWN) {
                if (timeDelay != 50) {
                    timeDelay = 50;
                    scheduledExecutorService.shutdown();
                    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                    scheduledExecutorService.scheduleAtFixedRate(gameTick, timeDelay, timeDelay, TimeUnit.MILLISECONDS);
                }
                lbScore.addToScore();
            }

        });
        gpGameField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                timeDelay = 400;
                scheduledExecutorService.shutdown();
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                scheduledExecutorService.scheduleAtFixedRate(gameTick, timeDelay, timeDelay, TimeUnit.MILLISECONDS);
            }
        });
    }

    public Shape getRandomShape() {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                return new ShapeI();
            case 1:
                return new ShapeJ();
            case 2:
                return new ShapeL();
            case 3:
                return new ShapeO();
            case 4:
                return new ShapeS();
            case 5:
                return new ShapeT();
            case 6:
                return new ShapeZ();
        }
        return null;
    }

    public void setCloseRequeast(Stage primaryStage) {
        primaryStage.setOnCloseRequest(event -> {
            scheduledExecutorService.shutdown();
        });
    }

    public void setFocus() {
        gpGameField.requestFocus();
    }
}
