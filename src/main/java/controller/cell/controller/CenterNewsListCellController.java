package controller.cell.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:48 2018/05/29
 * @Modificd :
 */
public class CenterNewsListCellController {
    @FXML
    private AnchorPane root;
    @FXML
    private Label title;
    @FXML
    private Label time;

    public CenterNewsListCellController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/center_news_list_cell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void initData(String name,String date){
        title.setText(name);
        time.setText(date);
    }

    public AnchorPane getRoot(){
        return  root;
    }

}
