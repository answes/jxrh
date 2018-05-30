package controller.pup;

import bean.News;
import controller.pup.controller.CenterNewsPupController;
import controller.pup.controller.NewsPupController;
import javafx.collections.ObservableList;
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
 * @Date : Create in 10:21 2018/05/30
 * @Modificd :
 */
public class CenterNewsPupWindow {
    Stage stage;

    Parent root = null;
    CenterNewsPupController newsPupController;

    public CenterNewsPupWindow(News news){
        stage = new Stage();
        stage.setTitle(news.getTilte()+news.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/center_news_pup.fxml"));
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        newsPupController = loader.getController();
        newsPupController.setData(stage,news);
        //设置模态 使得前面的窗口不可编辑
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setData(News news){
        stage.setTitle(news.getTilte()+news.getId());
        newsPupController.setData(stage,news);
    }
}
