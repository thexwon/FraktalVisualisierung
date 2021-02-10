package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    Canvas canvas;
    @FXML
    AnchorPane canvasAnchor;


    private double divisor=2;
    private int x;
    private int y;
    private int newx;
    private int newy;
    private int pointCounter = 0;
    private boolean canvasBit = true;
    private int[] points = new int[40];
    private boolean startStop = true;
    private int randomInt =0;
    private Math m;
    private int iteration=0;
    @FXML
    private void canvasClick() {


        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.DARKRED);
                if (canvasBit) {
                    x = (int) (event.getSceneX() - canvasAnchor.getLayoutX());

                    y = (int) (event.getSceneY() - canvasAnchor.getLayoutY());

                    gc.fillOval(x, y, 5, 5);

                    System.out.println("Point set at x: " + x + "\ty: " + y + "\tCurrent amount of points: " + pointCounter);

                    if (pointCounter >= 20) {
                        canvasBit = false;
                    }

                    points[pointCounter]=x;
                    points[pointCounter+19]=y;
                    pointCounter++;
                }
            }
        });


    }


    @FXML
    private void buttonActionSTART() {
         GraphicsContext gc = canvas.getGraphicsContext2D();
         gc.setFill(Color.WHITE);

        randomInt = (int) (m.random()*pointCounter);
        x = points[randomInt];
        y = points[randomInt+19];

        while(startStop){

            randomInt = (int) (m.random()*pointCounter);
            newx = points[randomInt];
            newy = points[randomInt+19];

            x = (int) (x - (x-newx)/divisor);
            y = (int) (y - (y-newy)/divisor);                            //wtf passiert bei  x = m.abs(x-newx)/2;  y = m.abs(y-newy)/2;
            gc.fillOval(x,y,1,1);
            iteration++;
            if(iteration ==300000){
                startStop =false;
            }
        }
        startStop =true;
        iteration = 0;

    }

    @FXML
    private void buttonActionCLEAR() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        startStop = true;
        iteration = 0;
        pointCounter = 0;
        canvasBit = true;
    }
}
