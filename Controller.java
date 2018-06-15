package sample;


import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller extends Application {

    @Override
    public void start(Stage stage) {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        ArrayList<Integer> dxs = new ArrayList<Integer>();
        ArrayList<Integer> dys = new ArrayList<Integer>();
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 300, 300, Color.ALICEBLUE);
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();
        Button btn = new Button();
        btn.setText("press");
        canvas.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Circle ball = new Circle(10, Color.CADETBLUE);
                ball.relocate(5, 5);
                canvas.getChildren().add(ball);
                circles.add(ball);
                dxs.add(7);
                dys.add(3);
            }
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                for(int i = 0;i<circles.size();i++) {
                    Circle ball = circles.get(i);
                    int dx = dxs.get(i);
                    int dy = dys.get(i);
                    ball.setLayoutX(ball.getLayoutX() + dx);
                    ball.setLayoutY(ball.getLayoutY() + dy);
                    Bounds bounds = canvas.getBoundsInLocal();
                    if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) ||
                            ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){
                        dxs.set(i, -dx);
                    }
                    if((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) ||
                            (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){
                        dys.set(i, -dy);

                    }
                }

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}