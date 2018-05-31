package controller.pup;

import controller.pup.controller.AboutController;
import controller.pup.controller.PasswordController;
import controller.pup.controller.SystemSetController;
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
 * @Date : Create in 15:45 2018/05/31
 * @Modificd :
 */
public class CommonPupWindow {
    Stage stage;

    AboutController aboutController;
    SystemSetController systemSetController;

    public CommonPupWindow(int type) {
        Parent root = null;
        String url=null;
        switch (type){
            case 1:
                url = "fxml/about.fxml";
                break;
            case 2:
                url = "fxml/system_set.fxml";
                break;
        }
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(url));
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        switch (type){
            case 1:
                aboutController = loader.getController();
                aboutController.setStage(stage);
                break;
            case 2:
                systemSetController = loader.getController();
                systemSetController.setStage(stage);
                break;
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
