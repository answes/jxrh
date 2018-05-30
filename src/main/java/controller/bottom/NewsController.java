package controller.bottom;

import bean.News;
import controller.cell.CenterNewsCell;
import controller.cell.NewsListLeftCell;
import controller.pup.CenterNewsPupWindow;
import controller.pup.NewsPupWindow;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 16:04 2018/05/24
 * @Modificd :
 */
public class NewsController implements Initializable{
    ObservableList<News> list = FXCollections.observableArrayList(new News(1L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进"),new News(2L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进")
            ,new News(3L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进"),new News(4L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进"),new News(5L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进"),
            new News(6L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进一带一路”政策密集出台 今年有望纵深推进"),new News(7L,"2018-05-05 08:45:26","一带一路”政策密集出台 今年有望纵深推进"));

    @FXML
    private ListView<News> lvBulletin;
    @FXML
    private ListView<News> lvInfo;
    @FXML
    private ListView<News> lvNews;

    private VBox image;
    @FXML
    private GridPane root;


    public void initDate() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        createVb();
        lvBulletin.setItems(list);
        lvBulletin.setCellFactory(param -> new CenterNewsCell());
        lvInfo.setItems(list);
        lvInfo.setCellFactory(param -> new CenterNewsCell());
        lvNews.setItems(list);
        lvNews.setCellFactory(param -> new CenterNewsCell());
        lvBulletin.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new CenterNewsPupWindow(new_val);
                });
        lvInfo.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new CenterNewsPupWindow(new_val);
                });
        lvNews.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends News> ov, News old_val,
                 News new_val) -> {
                    new CenterNewsPupWindow(new_val);
                });
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue()>1200){
                root.add(image,3,0);
            }else{
                if(root.getChildren().size()>=4){
                    root.getChildren().remove(3);
                }
            }
        });
    }

    private void createVb() {
        image = new VBox();
        image.setPrefHeight(200);
        HBox label = new HBox();
        label.setPrefHeight(28);
        label.setStyle("-fx-background-color: linear-gradient(to bottom,#2C2B2B, #202021);");
        image.getChildren().add(label);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(171);
        imageView.setImage(new Image(getClass().getClassLoader().getResource("image/login_bg.jpg").toExternalForm()));
        image.getChildren().add(imageView);

    }
}
