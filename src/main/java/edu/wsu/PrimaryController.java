package edu.wsu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PrimaryController {

    public String name;

    @FXML
    private Button startButton;

    @FXML
    private Button cancelButton;


    @FXML
    Label settings;

    @FXML
    TextField playerName;

    @FXML
    ComboBox<Integer> snakeLength;

    @FXML
    ColorPicker snakeColor;

    @FXML
    ColorPicker snakeHeadColor;

    @FXML
    ComboBox<Integer> numberOfFruits;

    @FXML
    ColorPicker fruitColor;

    @FXML
    ComboBox<Integer> gridWidth;

    @FXML
    ComboBox<Integer> gridHeight;

    public void initialize(){

        System.out.println("Primary controller initialize");
        for(int i = 1; i <= 10; i++){ //set snake length values
            snakeLength.getItems().add(i);
        }
        snakeLength.setValue(4);

        for(int i = 3; i <= 19; i++){ //set grid width values
            gridWidth.getItems().add(i);
        }
        gridWidth.setValue(6);

        for(int i = 2; i <= 10; i++){ //set grid height values
            gridHeight.getItems().add(i);
        }
        gridHeight.setValue(4);

        for(int i = 1; i <= 5; i++){ //set grid width values
            numberOfFruits.getItems().add(i);
        }
        numberOfFruits.setValue(2);


        //I ended up setting colors through the fxml file
    }

    @FXML
    public void startGame(ActionEvent event){
        System.out.println("Player name = " + playerName.getText());
        System.out.println("Snake length = " + snakeLength.getValue());
        System.out.println("Selected color = " + snakeColor.getValue());
        name = playerName.getText();

        Node source = (Node) event.getSource();
        Window theStage = source.getScene().getWindow();

        SnakePane snakePane = new SnakePane(gridWidth.getValue(), gridHeight.getValue(),
        snakeLength.getValue(), numberOfFruits.getValue(), snakeColor.getValue(),
        snakeHeadColor.getValue(), fruitColor.getValue(), playerName.getText());

        snakePane.start((Stage) theStage);
    }

    @FXML
    public void stopGame(){ //code was gotten from stack overflow
        Stage theStage = (Stage) cancelButton.getScene().getWindow();
        System.out.println("exited game");
        theStage.close();

    }

    public String getName(){
        return name;
    }
}
