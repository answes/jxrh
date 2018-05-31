package controller.pup;

import controller.pup.controller.WRNotesController;
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
 * @Date : Create in 9:47 2018/05/31
 * @Modificd :
 */
public class WRNotesPupWindow {

    Stage stage;
    Parent root = null;
    WRNotesController wrNotesController;

    public WRNotesPupWindow() {
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/wr_notes.fxml"));
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        wrNotesController = loader.getController();
        wrNotesController.setStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
