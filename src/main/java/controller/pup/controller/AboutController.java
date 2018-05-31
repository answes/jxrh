package controller.pup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 15:31 2018/05/31
 * @Modificd :
 */
public class AboutController implements Initializable{

    private Stage stage;

    public void exit(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
