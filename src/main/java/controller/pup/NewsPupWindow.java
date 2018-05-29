package controller.pup;

import bean.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 10:30 2018/05/29
 * @Modificd :
 */
public class NewsPupWindow {
    Stage stage;

    public NewsPupWindow(News news){
        stage = new Stage();
        stage.setTitle(news.getTilte()+news.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/news_pup.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();

    }

    public void setData(News news){
        stage.setTitle(news.getTilte()+news.getId());
    }
}
