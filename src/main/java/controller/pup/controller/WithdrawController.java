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
public class WithdrawController implements Initializable{

    @FXML
    private TextField name;
    @FXML
    private Button submit;
    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> bank;
    @FXML
    private TextField money;
    @FXML
    private Label canOutMoney,lbTitle;
    @FXML
    private TextField capital;
    @FXML
    private TextField remarks;

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
        ObservableList<String > bankData= FXCollections.observableArrayList("恒丰通道");
        bank.setItems(bankData);
        bank.setValue("恒丰通道");
    }

    public void setStage(Stage stage){
        this.stage =stage;
    }

    public void close(ActionEvent event) {
        stage.close();
    }
}
