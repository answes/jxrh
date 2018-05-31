package controller.pup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 16:40 2018/05/31
 * @Modificd :
 */
public class SystemSetController implements Initializable {
    @FXML
    private ComboBox<String> cb1,cb2,cb3,cb4,cb5;
    private Stage stage;
    public void exit(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb1.getItems().addAll("正常","提醒");
        cb1.setValue("正常");
        cb2.getItems().addAll("提示","不提示");
        cb2.setValue("提示");
        cb3.getItems().addAll("提示","不提示");
        cb3.setValue("提示");
        cb4.getItems().addAll("提示","不提示");
        cb4.setValue("提示");
        cb5.getItems().addAll("1小时","30分钟","15分钟");
        cb5.setValue("30分钟");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void submit(ActionEvent event) {
        stage.close();
    }
}
