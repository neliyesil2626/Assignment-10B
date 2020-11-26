package edu.wsu;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PrimaryController {

    @FXML
    private Button startButton;

    @FXML
    TextField playerName;

    @FXML
    ComboBox<Integer> snakeLength;

    @FXML
    ColorPicker snakeColor;

    public void initialize(){
        System.out.println("Primary controller initialize");
        for(int i = 1; i <= 10; i++){
            snakeLength.getItems().add(i);
        }
        snakeLength.setValue(4);
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        System.out.println(startButton.getText() + " is clicked");
        System.out.println("Player name = " + playerName.getText());
        System.out.println("Snake length = " + snakeLength.getValue());
        System.out.println("Selected color = " + snakeColor.getValue());
        Node source = (Node) actionEvent.getSource();
        Window theStage = source.getScene().getWindow();

        SnakePane snakePane = new SnakePane();
        snakePane.start((Stage) theStage);
    }
}
