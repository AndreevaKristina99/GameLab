package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

public class TimerBar {

    private final Timer timer;
    private final GraphicsContext graphicsContext;
    private double progress;

    public TimerBar(GraphicsContext gc){
        graphicsContext = gc;
        progress = 100.;
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        };
        timer.schedule(timerTask, 1, 10);
    }

    void draw(){
        graphicsContext.setFill(Color.ORANGE);
        graphicsContext.fillRect(20,20,460,50);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeRect(20, 20, 460, 50);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(21,21,460*progress/100.-1,48);
        progress-=0.02;

        if(progress<0.02) {
            timer.cancel();
            graphicsContext.strokeText("Время вышло",200,50);
        }
    }

    public double getProgress() {
        return progress;
    }
}
