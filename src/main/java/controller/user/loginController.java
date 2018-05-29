package controller.user;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.Constant;
import utils.ControlledStage;
import utils.StageController;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:55 2018/05/21
 * @Modificd :
 */
public class loginController implements ControlledStage,Initializable {
    @FXML
    private ImageView code;
    @FXML
    private Pane loginTitle;

    private StageController myController;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        code.setImage(new Image(getClass().getClassLoader().getResource("image/no.png").toExternalForm()));
        loginTitle.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        loginTitle.setOnMouseDragged(event -> {
            event.consume();
            myController.getStage(Constant.LOGIN_ID).setX(event.getScreenX() - xOffset);
            //根据自己的需求，做不同的判断
            if (event.getScreenY() - yOffset < 0) {
                myController.getStage(Constant.LOGIN_ID).setY(0);
            } else {
                myController.getStage(Constant.LOGIN_ID).setY(event.getScreenY() - yOffset);
            }
        });
    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;
    }

    public void login(Event event){

        myController.setStage(Constant.ADVICE_ID,Constant.LOGIN_ID);

    }

    public void register(Event event){
        myController.setStage(Constant.REGISTER_ID,Constant.LOGIN_ID);
    }

    public void exit(Event event){
        myController.getStage(Constant.LOGIN_ID).close();
//        Platform.exit();
    }

    public void forgetPassword(Event event){
        try {
            Desktop.getDesktop().browse(new URI("http://www.baidu.com/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
