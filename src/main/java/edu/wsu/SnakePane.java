package edu.wsu;

import java.util.LinkedList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;


/**
 * JavaFX App
 */
public class SnakePane extends Application {
  private int bodyLength = 10;
  private Color bodyColor = Color.LIMEGREEN;
  private Color headColor = Color.RED;
  private String name = "Nick";

//  public SnakePane(int l, Color bc, Color hc, String n){
//    bodyLength = l;
//    bodyColor = bc;
//    headColor = hc;
//    name = n;
//  }

  private List<Circle> createSnake(){
    List<Circle> snake = new LinkedList<>();

    int radius = 10;

    var head = new Circle(300,200, radius, headColor);
    snake.add(head);

    int bodyLength = 10;
    for(int i = 1; i <=bodyLength; i++){
      Circle bodySegment = new Circle(300 - (i * radius * 2), 200,radius,bodyColor);
      snake.add(bodySegment);
    }
    return snake;
  }

  private void drawSnake(List<Circle> snake, Pane pane){
    pane.getChildren().addAll(snake);
  }

  private void moveSnakeRight(List<Circle> snake){
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX((oldHead.getCenterX() + (2 * radius))%600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
  }

  private void moveSnakeLeft(List<Circle> snake){
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX((oldHead.getCenterX() - (2 * radius) + 600)%600);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
  }

  private void moveSnakeUp(List<Circle> snake){
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX(oldHead.getCenterX());
    newHead.setCenterY((oldHead.getCenterY() - (2 * radius) +400)%400);
    snake.add(0, newHead);
  }

  private void moveSnakeDown(List<Circle> snake) {
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX(oldHead.getCenterX());
    newHead.setCenterY((oldHead.getCenterY() + (2 * radius))%400);
    snake.add(0, newHead);
  }

  private void moveSnake(List<Circle> snake){
    if(snake.get(0).getCenterX() > snake.get(1).getCenterX()){ //snake facing right
      moveSnakeRight(snake);
    } else if(snake.get(0).getCenterX() < snake.get(1).getCenterX()){
      moveSnakeLeft(snake);
    } else if(snake.get(0).getCenterY() > snake.get(1).getCenterY()){
      moveSnakeDown(snake);
    } else {
      moveSnakeUp(snake);
    }
  }


  @Override
  public void start(Stage stage) {
    var pane = new AnchorPane();
    pane.setPrefSize(600,400);

    List<Circle> snake = createSnake();
    drawSnake(snake, pane);

    AnimationTimer time = new AnimationTimer(){
      private long lastUpdate; //code found from stack overflow
      @Override
      public void handle(long now) {
        if(now - lastUpdate >= 38000000){
          moveSnake(snake);
          lastUpdate = now;
        }
      }
    };

    time.start();

    var scene = new Scene(pane);
    stage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      switch(event.getCode()){
        case UP:
          moveSnakeUp(snake);
          break;
        case DOWN:
          moveSnakeDown(snake);
          break;
        case RIGHT:
          moveSnakeRight(snake);
          break;
        case LEFT:
          moveSnakeLeft(snake);
          break;

        default:
          // do nothing
      }
    });
    pane.requestFocus();
    stage.show(); //shows the screen
  }

  public static void main(String[] args) {
    launch();
  }

}
