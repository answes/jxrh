package controller.bottom;

import bean.News;
import controller.cell.NewsListLeftCell;
import controller.pup.NewsPupWindow;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 16:04 2018/05/24
 * @Modificd :
 */
public class NewsController implements Initializable{
    ObservableList<News> list = FXCollections.observableArrayList(new News(1L,"2018-05-05 00:00:00","一带一路”政策密集出台 今年有望纵深推进"),new News(2L,"2018-05-05 00:00:00","新闻标题不想说什么")
            ,new News(3L,"2018-05-05 00:00:00","新闻标题不想说什么"),new News(4L,"2018-05-05 00:00:00","新闻标题不想说什么"),new News(5L,"2018-05-05 00:00:00","新闻标题不想说什么"));

    @FXML
    private ListView<News> lvBulletin;
    @FXML
    private ListView<News> lvInfo;
    @FXML
    private ListView<News> lvNews;


    public void initDate() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        lvBulletin.setItems(list);
        lvBulletin.setCellFactory(param -> new NewsListLeftCell());
        lvInfo.setItems(list);
        lvInfo.setCellFactory(param -> new NewsListLeftCell());
        lvNews.setItems(list);
        lvNews.setCellFactory(param -> new NewsListLeftCell());
        lvBulletin.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new NewsPupWindow(list,new_val);
                });
        lvInfo.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new NewsPupWindow(list,new_val);
                });
        lvNews.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new NewsPupWindow(list,new_val);
                });
    }
}
