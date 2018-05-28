package controller.center;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 17:37 2018/05/28
 * @Modificd :
 */
public class NewCenterController implements Initializable {
    @FXML
    private AnchorPane news_root;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRootVisible(boolean rootVisible) {
    if(rootVisible){
        news_root.setVisible(true);
    }else {
        news_root.setVisible(false);
    }
    }
}
