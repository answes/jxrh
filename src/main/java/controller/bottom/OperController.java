package controller.bottom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 15:46 2018/05/24
 * @Modificd :
 */
public class OperController implements Initializable {
    @FXML
    private AnchorPane unLock;
    @FXML
    private VBox lock;
    private boolean isLock = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unLock.setVisible(true);
        lock.setVisible(false);
    }

    public void setLock(){
        unLock.setVisible(false);
        lock.setVisible(true);
        isLock = true;
    }
    public boolean isLock(){
        return isLock;
    }

    public void btUnLock(ActionEvent event) {
        isLock= false;
        unLock.setVisible(true);
        lock.setVisible(false);
    }
}
