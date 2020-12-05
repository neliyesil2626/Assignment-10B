package edu.wsu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EndScreenController {

  String name = "nick";
  int score = 10;

  @FXML
  Label line1;

  @FXML
  Label line2;

  public void initialize(){
    name = App.getName();
    score = App.getScore();

    System.out.println("Name is: " + name);
    line1.setText("Game Over, " + name);
    line2.setText("Score: "+ score);
  }

}
