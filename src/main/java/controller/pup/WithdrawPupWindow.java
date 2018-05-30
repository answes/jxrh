package controller.pup;

import controller.pup.controller.NewsPupController;
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
public class WithdrawPupWindow {
    Stage stage;
    Parent root = null;
    WithdrawController withdrawController;

    public WithdrawPupWindow() {
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/withdraw.fxml"));
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        withdrawController = loader.getController();
        withdrawController.setStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


}
