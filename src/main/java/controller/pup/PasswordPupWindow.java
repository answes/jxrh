package controller.pup;

import controller.pup.controller.PasswordController;
import controller.pup.controller.WithdrawController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 17:27 2018/05/30
 * @Modificd :
 */
public class PasswordPupWindow {
    Stage stage;
    Parent root = null;
    PasswordController withdrawController;

    public PasswordPupWindow(int type) {
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/password.fxml"));
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        withdrawController = loader.getController();
        withdrawController.setStage(stage,type);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


}
