package sample.Shapes;


import javafx.scene.paint.Color;

import java.util.Random;

public class Shape{
    boolean[][] filledRects;
    Color color;

    public Shape() {
        Random random = new Random();
        color = Color.rgb(random.nextInt()*240, random.nextInt()*240, random.nextInt()*240);
    }
    //TODO:Draw, Darstellung
}

