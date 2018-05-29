package controller.pup.controller;

import bean.News;
import controller.cell.NewsListCell;
import controller.cell.NewsListLeftCell;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:30 2018/05/29
 * @Modificd :
 */
public class NewsPupController implements Initializable{
    @FXML
    private ListView<News> lvNews;
    @FXML
    private WebView wvWeb;
    private WebEngine webEngine;

    ObservableList<News> data;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void init() {
        wvWeb.setContextMenuEnabled(false);
        webEngine = wvWeb.getEngine();
        webEngine.load("https://www.baidu.com");
        lvNews.setItems(data);
        lvNews.setCellFactory(param -> new NewsListLeftCell());
        lvNews.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) ->webEngine.load("https://www.sina.com"));

    }

    public void setData(ObservableList<News> list,News news){
        if(list!=null){
            this.data = list;
            init();
        }
        data.forEach(n->{
            if(n.getId()==news.getId()){
                System.out.println(n.getTilte()+n.getId());
            }
        });
    }

}
