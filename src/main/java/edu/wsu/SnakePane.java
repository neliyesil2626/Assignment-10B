package edu.wsu;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.stage.Window;


/**
 * JavaFX App
 */
public class SnakePane extends Application {

  private int radius = 10;
  private int width; //window width
  private int height; //window height
  private int direction = 2; //0 = up, 1 = down, 2 = right, 3 = left
  private int bodyLength;
  private int score;
  private int numFruits;
  private Color bodyColor;
  private Color headColor;
  private Color fruitColor;
  private String name;

  private AnimationTimer time;
  private List<Circle> snake;
  private List<Circle> fruits;

  public SnakePane(){
    width = 600;
    height = 400;
    bodyLength = 10;
    score = 0;
    numFruits = 2;
    bodyColor = Color.LIMEGREEN;
    headColor = Color.RED;
    fruitColor = Color.RED;
    name = "Snake";
    snake = new LinkedList<>();
  }

  public SnakePane(int width, int height, int bodylength, int numFruits, Color bodyColor, Color headColor, Color fruitColor, String name){
    this.width = width * 100;
    this.height = height * 100;
    this.bodyLength = bodylength;
    score = 0;
    this.numFruits = numFruits;
    this.bodyColor = bodyColor;
    this.headColor = headColor;
    this.fruitColor = fruitColor;
    this.name = name;
    snake = new LinkedList<>();
  }

  private List<Circle> createSnake(){
    List<Circle> body = new LinkedList<>();

    var head = new Circle((width - (radius * 2))/2,(height - (radius * 2))/2, radius, headColor);
    body.add(head);

    for(int i = 1; i <= bodyLength; i++){
      Circle bodySegment = new Circle((width - (radius * 2))/2 - (i * radius * 2), (height - (radius * 2))/2,radius,bodyColor);
      body.add(bodySegment);
    }

    return body;
  }

  private void drawSnake(List<Circle> snake, Pane pane){ pane.getChildren().addAll(snake); }

  private List<Circle> createFruits(){
    List <Circle> fruits = new LinkedList<>();
    while(fruits.size() < numFruits){
      Circle fruit = createFruit();
      if(fruits.size() != 0) {
        for (int i = 0; i < fruits.size(); i++) {
          if(fruit.getCenterY() == fruits.get(i).getCenterY() && fruit.getCenterX() == fruits.get(i).getCenterX()){
            continue;
          }
        }
      }
      fruits.add(fruit);
    }
    return fruits;
  }

  private Circle createFruit(){
    Circle fruit;
    Random location = new Random();
    int x = location.nextInt(width/(2 * radius)) * (2 * radius) + radius;
    int y = location.nextInt(height/(2 * radius)) * (2 * radius) + radius;
    fruit = new Circle(x,y, radius,fruitColor);
    return fruit;
  }

  private void drawFruit(List<Circle> fruits, Pane pane){
    pane.getChildren().addAll(fruits);
  }


/**After the snake hits a fruit this method is called to increase the size of the snake and move
 * the fruit that the snake ate.
 * @index the index of the fruit that the snake hit.**/
  private void eatFruit(Pane pane, int index){
    score++;
    bodyLength++;
    Circle bodySegment = new Circle(radius,bodyColor);
    switch(direction){
      case 0: //snake faces up
        bodySegment.setCenterX(snake.get(snake.size() - 1).getCenterX());
        bodySegment.setCenterY(snake.get(snake.size() - 1).getCenterY() + (radius * 2));
        break;
      case 1: //snake faces down
        bodySegment.setCenterX(snake.get(snake.size() - 1).getCenterX());
        bodySegment.setCenterY(snake.get(snake.size() - 1).getCenterY() - (radius * 2));
        break;
      case 2: //snake faces right
        bodySegment.setCenterX(snake.get(snake.size() - 1).getCenterX() - (radius * 2));
        bodySegment.setCenterY(snake.get(snake.size() - 1).getCenterY());
        break;
      case 3: //snake faces left
        bodySegment.setCenterX(snake.get(snake.size() - 1).getCenterX() + (radius * 2));
        bodySegment.setCenterY(snake.get(snake.size() - 1).getCenterY());
        break;
      default:
        System.out.println("Error: wasn't facing a direction");
    }
    pane.getChildren().remove(fruits.get(index));
    fruits.set(index, createFruit());
    snake.add(bodySegment);
    pane.getChildren().add(fruits.get(index));
    pane.getChildren().add(snake.get(snake.size() - 1));
  }

  private void moveSnakeRight(List<Circle> snake){
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX((oldHead.getCenterX() + (2 * radius))%width);
    newHead.setCenterY(oldHead.getCenterY());
    snake.add(0, newHead);
  }

  private void moveSnakeLeft(List<Circle> snake){
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX((oldHead.getCenterX() - (2 * radius) + width)%width);
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
    newHead.setCenterY((oldHead.getCenterY() - (2 * radius) +height)%height);
    snake.add(0, newHead);
  }

  private void moveSnakeDown(List<Circle> snake) {
    int radius = 10;
    Circle oldHead = snake.get(0);
    Circle newHead = snake.remove(snake.size() - 1);
    oldHead.setFill(bodyColor);
    newHead.setFill(headColor);
    newHead.setCenterX(oldHead.getCenterX());
    newHead.setCenterY((oldHead.getCenterY() + (2 * radius))%height);
    snake.add(0, newHead);
  }

  private void moveSnake(List<Circle> snake){
    if(direction == 2){ //snake facing right
      moveSnakeRight(snake);
    } else if(direction == 3){ //snake is facing left
      moveSnakeLeft(snake);
    } else if(direction == 1){ //snake is facing down
      moveSnakeDown(snake);
    } else {
      moveSnakeUp(snake);
    }
  }

  private void hitScreen(Stage stage, Pane pane){
    if( (snake.get(0).getCenterX() <= radius && direction == 3) || (snake.get(0).getCenterX() >= width - radius && direction == 2)
        || (snake.get(0).getCenterY() <= radius && direction == 0) || (snake.get(0).getCenterY() >= height - radius && direction == 1) ){ //snake has hit edge
      try{
        end(stage, pane);
      } catch (Exception e){
        System.out.println("Error code: " + e);
      }

    }
  }

  private void hitFruit(Stage stage, Pane pane){
    for(int i = 0; i < fruits.size(); i++){
      if(snake.get(0).getCenterX() == fruits.get(i).getCenterX() && snake.get(0).getCenterY() == fruits.get(i).getCenterY()){
        eatFruit(pane,i);
      }
    }
  }

  private void hitSelf(Stage stage, Pane pane){
    for(int i = 1; i < snake.size(); i++){
      if(snake.get(0).getCenterX() == snake.get(i).getCenterX() && snake.get(0).getCenterY() == snake.get(i).getCenterY()){
        try{
          end(stage, pane);
        } catch (Exception e){
          System.out.println("Error code: " + e);
        }
      }
    }
  }

  @Override
  public void start(Stage stage) {

    var pane = new AnchorPane();
    pane.setPrefSize(width,height);

    snake = createSnake();
    fruits = createFruits();

    drawSnake(snake, pane);
    drawFruit(fruits, pane);

    time = new AnimationTimer(){
      private long lastUpdate; //code found from stack overflow
      @Override
      public void handle(long now) {
        if(now - lastUpdate >= 76000000){
          hitSelf(stage, pane);
          hitScreen(stage, pane);
          hitFruit(stage, pane);
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
          direction = 0;
          break;
        case DOWN:
          direction = 1;
          break;
        case RIGHT:
          direction = 2;
          break;
        case LEFT:
          direction = 3;
          break;

        default:
          // do nothing
      }
    });
    pane.requestFocus();
    stage.show(); //shows the screen

  }

  public void end(Stage stage, Pane pane) throws IOException {
    time.stop();

    App.setName(name);
    App.setScore(score);

    Scene endScreen = new Scene(App.loadFXML("endscreen"),640,480);
    stage.setScene(endScreen);
    stage.show();

    System.out.println("GameOver");
  }

  public static void main(String[] args) {
    launch();
  }

}
