package controller.pup.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 17:33 2018/05/30
 * @Modificd :
 */
public class PasswordController implements Initializable{

    @FXML
    private Label lbTitle;

    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //记录窗口位置
        lbTitle.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //改变窗口位置
        lbTitle.setOnMouseDragged(event -> {
            event.consume();
            stage.setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    stage.setY(0);
                } else {
                    stage.setY(event.getScreenY() - yOffset);
                }
        });
    }

    public void setStage(Stage stage){
        this.stage =stage;
    }

    public void close(ActionEvent event) {
        stage.close();
    }
}
