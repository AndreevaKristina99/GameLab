package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Controller{

    @FXML
    private Label scoreLabel;

    @FXML
    private Canvas timerBarCanvas;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Canvas canvas;

    private TimerBar timerBar;
    private int score = 0;
    private int number;

    @FXML
    void initialize(){
        showRandomNumber();
        startButton.setOnAction(actionEvent -> {
            drawStaticElements();
            startButton.setVisible(false);
            canvas.setVisible(true);
        });
        canvas.setOnMouseClicked(mouseEvent -> {
            int x = (int)mouseEvent.getX(), y = (int)mouseEvent.getY();
            if(y>198 && y<277){
                if(x<122){
                    leftClicked();
                }
                if(x>378){
                    rightClicked();
                }
            }
        });
    }

    private boolean checkTime(){
        if(timerBar.getProgress()<0.02){
            return false;
        }
        return true;
    }

    private void drawStaticElements(){
        timerBar = new TimerBar(timerBarCanvas.getGraphicsContext2D());
        scoreLabel.setText("Score: 0");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(new Image("images/left.png"),20,200, 100,75);
        graphicsContext.drawImage(new Image("images/right.png"), 380, 200, 100, 75);
    }

    private void showRandomNumber(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        number = (int)(90*Math.random()+10);
        gc.setFill(Color.ORANGE);
        gc.fillRect(150, 150, 200, 300);
        gc.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 96));
        gc.setFill(Color.BLACK);
        gc.fillText(number+"",180,260);
    }

    private void leftClicked(){
        if(checkTime()){
            if(number%2==1){
                score++;
            } else {
                score-=2;
            }
            scoreLabel.setText("Score: "+score);
            showRandomNumber();
        }
    }

    private void rightClicked(){
        if(checkTime()){
            if(number%2==0){
                score++;
            } else {
                score-=2;
            }
            scoreLabel.setText("Score: "+score);
            showRandomNumber();
        }
    }
}