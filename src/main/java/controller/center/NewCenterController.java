package controller.center;

import bean.News;
import controller.cell.NewsListCell;
import controller.pup.NewsPupWindow;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 17:37 2018/05/28
 * @Modificd :
 */
public class NewCenterController implements  Initializable {
    @FXML
    private AnchorPane news_root;
    @FXML
    private ListView<News> lv_news;
    @FXML
    private Label new1,new2,new3,new4,new5,new6,new7,new8;
    @FXML
    private NewsPupWindow newsPupWindow;

    private String hover = "-fx-background-color: transparent;" +
            "-fx-text-fill:white;" +
            "-fx-padding: 0 0 0 20px;" +
            "-fx-graphic-text-gap:10px;"+"-fx-";
    private String hide = "";

    ObservableList<News> list = FXCollections.observableArrayList(new News(1L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进"),new News(2L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进")
    ,new News(3L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进"),new News(4L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进"),new News(5L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        lv_news.setItems(list);
        lv_news.setCellFactory(param -> new NewsListCell());
        lv_news.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    newsPupWindow = new NewsPupWindow(list,new_val);
                });
    }


    public void setRootVisible(boolean rootVisible) {
    if(rootVisible){
        news_root.setVisible(true);
    }else {
        news_root.setVisible(false);
    }
    }
}
