package controller;

import controller.center.GoodController;
import controller.center.NewCenterController;
import controller.center.TradeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utils.Constant;
import utils.ControlledStage;
import utils.DateUtil;
import utils.StageController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:28 2018/05/21
 * @Modificd :
 */
public class MainController implements ControlledStage ,Initializable{
    @FXML
    private Label top_title;
    @FXML
    private AnchorPane center_root;
    @FXML
    private AnchorPane right_root;
    @FXML
    private Label timeNow;
    @FXML
    private javafx.scene.control.Button to_transaction;
    @FXML
    private AnchorPane trade;

    private double xOffset = 0;
    private double yOffset = 0;
    private Date oldDate=new Date();
    private int count = 0;

    private StageController myController;
    private GoodController goodController;
    private TradeController tradeController;
    private NewCenterController newCenterController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }



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



        //系统时间显示
        Timeline timeline =  new Timeline(new KeyFrame(Duration.millis(1000), e ->  timeNow.setText(DateUtil.getNowTime())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        initNews();
        initTrade();
        initCenter();
    }

    private void initNews() {
        AnchorPane newsLoader = null;
        FXMLLoader newsLoaderFx = new FXMLLoader(getClass().getClassLoader().getResource("fxml/new_center.fxml"));
        try {
            newsLoader = newsLoaderFx.load();
            center_root.getChildren().add(newsLoader);
            newCenterController = newsLoaderFx.getController();
            newCenterController.setRootVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTrade() {
        AnchorPane kLoader = null;
        FXMLLoader kLoaderFx = new FXMLLoader(getClass().getClassLoader().getResource("fxml/trade.fxml"));
        try {
            kLoader = kLoaderFx.load();
            center_root.getChildren().add(kLoader);
            tradeController = kLoaderFx.getController();
            tradeController.setRootVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initCenter() {
        trade.managedProperty().bind(trade.visibleProperty());
        AnchorPane goodLoader = null;
        FXMLLoader goodLoaderFx = new FXMLLoader(getClass().getClassLoader().getResource("fxml/good.fxml"));
        try {
            goodLoader = goodLoaderFx.load();
            //这个是我把获取MainBottomController添加到这个Controller的底下布局，你不用管
            center_root.getChildren().add(goodLoader);
            //获取MainBottomController
            goodController = goodLoaderFx.getController();
            goodController.setRootVisible(true);
            goodController.setTradeController(tradeController);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        tradeController.showMinLine();
    }

    public void oneMinuteKLine(ActionEvent event) {
        tradeController.cycle(0);
    }

    public void fiveMinuteKLine(ActionEvent event) {
        tradeController.cycle(1);
    }

    public void fifteenMinuteKLine(ActionEvent event) {
        tradeController.cycle(2);
    }

    public void thrityMinuteKLine(ActionEvent event) {
        tradeController.cycle(3);
    }

    public void sixtyMinuteKLine(ActionEvent event) {
        tradeController.cycle(4);
    }

    public void dayKLine(ActionEvent event) {
        tradeController.cycle(5);
    }

    public void weekKLine(ActionEvent event) {
        tradeController.cycle(6);
    }

    public void monthKLine(ActionEvent event) {
        tradeController.cycle(7);
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
        goodController.setRootVisible(false);
        tradeController.setRootVisible(true);
        newCenterController.setRootVisible(false);
    }

    public void back(ActionEvent event) {
        goodController.setRootVisible(true);
        tradeController.setRootVisible(false);
        newCenterController.setRootVisible(false);
    }

    /**
     * 热点导航
     * @param event
     */
    public void hot(ActionEvent event) {
        trade.setVisible(true);
        goodController.setRootVisible(true);
        tradeController.setRootVisible(false);
        newCenterController.setRootVisible(false);
    }

    /**
     * 行情数据
     * @param event
     */
    public void data(ActionEvent event) {
        trade.setVisible(true);
        goodController.setRootVisible(true);
        tradeController.setRootVisible(false);
        newCenterController.setRootVisible(false);
    }

    /**
     * 新闻资讯
     * @param event
     */
    public void news(ActionEvent event) {
        trade.setVisible(false);
        goodController.setRootVisible(false);
        tradeController.setRootVisible(false);
        newCenterController.setRootVisible(true);
    }


}
