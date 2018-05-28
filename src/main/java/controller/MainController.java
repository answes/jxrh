package controller;

import bean.Goods;
import controller.bottom.NewsController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import chart.HQApplet;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import utils.Constant;
import utils.ControlledStage;
import utils.DateUtil;
import utils.StageController;

import javax.swing.*;
import java.awt.*;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:28 2018/05/21
 * @Modificd :
 */
public class MainController implements ControlledStage ,Initializable{
    @FXML
    private Pane top_title;
    @FXML
    private AnchorPane bottom_root;
    @FXML
    private AnchorPane goods_root;
    @FXML
    private TableView<Goods> tb_goods;

    @FXML
    private Label timeNow;

    @FXML
    private javafx.scene.control.Button to_transaction;

    private StageController myController;
    private NewsController newsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }
    private double xOffset = 0;
    private double yOffset = 0;
    private Date oldDate=new Date();
    private int count = 0;

    HQApplet jPanel = new HQApplet(0);
    SwingNode swingNode = new SwingNode();

    @FXML
    BorderPane view_root;

    @FXML
    HBox rightBox;

    int width  ;
    int height ;
    @FXML
    Pane kline;
    private void init() {
        //记录窗口位置
        top_title.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //改变窗口位置
        top_title.setOnMouseDragged(event -> {
            event.consume();
            if(!myController.getStage(Constant.MAIN_ID).isMaximized()){
                myController.getStage(Constant.MAIN_ID).setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    myController.getStage(Constant.MAIN_ID).setY(0);
                } else {
                    myController.getStage(Constant.MAIN_ID).setY(event.getScreenY() - yOffset);
                }
            }

        });
        //双击导航栏全屏，窗口缩放
        top_title.setOnMouseClicked(event -> {
            if(count==0){
                oldDate = new Date();
                count ++;
            }else{
                if(new Date().getTime()-oldDate.getTime()<1200){
                    boolean maxMin = !myController.getStage(Constant.MAIN_ID).isMaximized();
                    myController.getStage(Constant.MAIN_ID).setMaximized(maxMin);
                }
                count = 0;
            }

        });

        SwingUtilities.invokeLater(()->{
            jPanel.setFocusable(true);
            swingNode.setContent(jPanel);
            kline.getChildren().add(swingNode);
        });

        kline.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                width = newValue.intValue();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        repaintPanel();

                    }
                });
            }
        });


        kline.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                height = newValue.intValue() - 50;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        repaintPanel();

                    }
                });

            }
        });




        //系统时间显示
        Timeline timeline =  new Timeline(new KeyFrame(Duration.millis(1000), e ->  timeNow.setText(DateUtil.getNowTime())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        AnchorPane loader = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/news.fxml"));
        try {
            loader = fxmlLoader.load();
            //这个是我把获取MainBottomController添加到这个Controller的底下布局，你不用管
            bottom_root.getChildren().add(loader);
            //获取MainBottomController
            newsController = fxmlLoader.getController();
            //执行获取MainBottomController更新的方法,以后tableview 点击的时候就可以调用这个方法来更新第二个Controller的数据了
            newsController.initDate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重绘
     */
    private void repaintPanel() {

        swingNode.setContent(swingNode.getContent());
        swingNode.getContent().setPreferredSize(new Dimension(
                width, height));

        kline.setVisible(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    kline.setVisible(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void setStageController(StageController stageController) {
        this.myController = stageController;

    }

    public void historyQurey(ActionEvent event) {
    }


    public void complaints(ActionEvent event) {
    }

    public void addressManege(ActionEvent event) {
    }

    public void systemSet(ActionEvent event) {
    }

    public void aboutMe(ActionEvent event) {
    }

    public void signed(ActionEvent event) {
    }

    public void termination(ActionEvent event) {
    }

    public void outMoney(ActionEvent event) {
    }

    public void clientReport(ActionEvent event) {
    }

    public void calculator(ActionEvent event) {
    }

    public void refresh(ActionEvent event) {
    }

    public void restore(ActionEvent event) {
    }

    public void zoomWindow(ActionEvent event) {
        myController.getStage(Constant.MAIN_ID).setIconified(true);
    }

    boolean isMax = false;
    public void scaleWindow(ActionEvent event) {
        myController.getStage(Constant.MAIN_ID).setMaximized(!myController.getStage(Constant.MAIN_ID).isMaximized());
//        if(!isMax){
//            myController.setWindow(Constant.MAIN_ID);
//            isMax = true;
//        }else{
//            isMax=false;
//            myController.getStage(Constant.MAIN_ID).setMaximized(false);
//        }
    }

    public void closeWindow(ActionEvent event) {
        myController.getStage(Constant.MAIN_ID).close();
//        Platform.exit();
    }

    public void fenshiKLine(ActionEvent event) {
    }

    public void oneMinuteKLine(ActionEvent event) {
    }

    public void fiveMinuteKLine(ActionEvent event) {
    }

    public void tenMinuteKLine(ActionEvent event) {
    }

    public void fifteenMinuteKLine(ActionEvent event) {
    }

    public void thrityMinuteKLine(ActionEvent event) {
    }

    public void sixtyMinuteKLine(ActionEvent event) {
    }

    public void dayKLine(ActionEvent event) {
    }

    public void weekKLine(ActionEvent event) {
    }

    public void monthKLine(ActionEvent event) {
    }

    public void homeClick(MouseEvent event) {
    }

    public void kLineClick(MouseEvent event) {
    }

    public void productInformation(MouseEvent event) {
    }

    public void dealDetails(MouseEvent event) {
    }

    public void setIng(MouseEvent event) {
    }

    public void toTranstion(ActionEvent event) {
    }
}
