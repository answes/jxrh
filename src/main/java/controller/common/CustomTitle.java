package controller.common;

import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 10:44 2018/05/28
 * @Modificd :
 */
public class CustomTitle extends AnchorPane {
    @FXML
    private Label title;

    public CustomTitle(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/coustom_title.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void close(Event event){

    }

    public void max(Event event){

    }


}
