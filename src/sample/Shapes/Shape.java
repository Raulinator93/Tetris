package sample.Shapes;


import javafx.scene.paint.Color;

import java.util.Random;

public class Shape{
    protected boolean[][] filledRects;
    protected Color color;

    public Shape() {
        Random random = new Random();
        color = Color.rgb(random.nextInt(240), random.nextInt(240), random.nextInt(240));

    }
    public Shape(boolean[][] filledRects,Color color) {
       this.filledRects=filledRects;
        this.color=color;
    }
    public boolean[][] getFilledRects() {
        return filledRects;
    }

    public Color getColor() {
        return color;
    }
    public int getWidth(){
        return filledRects.length;
    }
    public int getHeight(){
        return filledRects[0].length;
    }
    public void rotate(){
        boolean[][] newRects= new boolean[filledRects[0].length][filledRects.length];
        for(int i=0;i<filledRects[0].length;i++){
            for(int j=0;j<filledRects.length;j++){
                newRects[i][j]=filledRects[j][filledRects[0].length-i-1];
            }
        }
        filledRects=newRects;
    }

    public Shape clone(){
        return new Shape(filledRects,color);
    }
}

