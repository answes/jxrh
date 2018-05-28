package controller;

import bean.Goods;
import chart.Draw_KLine;
import controller.bottom.NewsController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import chart.HQApplet;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
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
    private Pane top_title;
    @FXML
    private AnchorPane bottom_root;
    @FXML
    private AnchorPane goods_root;
    @FXML
    private AnchorPane kline_root;
    @FXML
    private TableView<Goods> tb_goods;
    /**
     * tableview Column
     */
    @FXML
    private TableColumn<Goods, String> commNum;
    @FXML
    private TableColumn<Goods, String> commName;
    @FXML
    private TableColumn<Goods, String> openPrice;
    @FXML
    private TableColumn<Goods, String> newPrice;
    @FXML
    private TableColumn<Goods, String> upDown;
    @FXML
    private TableColumn<Goods, String> extent;
    @FXML
    private TableColumn<Goods, String> maxPrice;
    @FXML
    private TableColumn<Goods, String> mixPrice;
    @FXML
    private TableColumn<Goods, String> yestedayPrice;
    @FXML
    private TableColumn<Goods, String> comePrice;
    @FXML
    private TableColumn<Goods, String> outPrice;
    @FXML
    private TableColumn<Goods, String> overNumber;
    @FXML
    private TableColumn<Goods, String> overMoney;

    @FXML
    private Label timeNow;
    @FXML
    HBox Indicator;
    @FXML
    Button MACD;

    private String normalCss =
            "-fx-background-color: #484848;"+
            "-fx-background-radius: 0px;" +
            "-fx-text-fill: #dddddd;" +
            "-fx-font-size: 10px;" +
            "-fx-padding: 1px 4px;"+
            "-fx-min-width: 40px;"+
            "-fx-start-margin: 0;" +
            "-fx-pref-height: 12px;" +
            "-fx-end-margin: 0;" +
            "-fx-border-color: #808080;"+
            "-fx-border-width:0 1px 0 0;" +
            "-fx-border-style: solid;" +
            "-fx-cursor: hand;";

    private String activedCss =
            "-fx-background-color: #D4D0C8;" +
            "-fx-text-fill: #FF0000;" +
            "-fx-font-size: 10px;" +
            "-fx-padding: 1px 4px;"+
            "-fx-border-width:0 1px 0 0;" +
            "-fx-start-margin: 0;" +
            "-fx-end-margin: 0;" +
            "-fx-border-style: solid;" +
            "-fx-border-color: #808080;" +
            "-fx-border-radius: 0px;" +
            "-fx-background-radius: 0px;" +
            "-fx-cursor: hand;"+
            "-fx-fill-height: true;"+
            "-fx-pref-height: 12px;"+
            "-fx-max-height: 12px;";

    @FXML
    private javafx.scene.control.Button to_transaction;

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

    private StageController myController;
    private NewsController newsController;
    private final ObservableList<Goods> datas = FXCollections.observableArrayList(new Goods(1, "80008", "人参糖", 12.0, 10.3, 30, 0.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
            , new Goods(2, "80008", "芝麻糖", 12.0, 10.3, 20, 1.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
            , new Goods(3, "80009", "百度糖", 22.0, 20.3, 30, 2.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
            , new Goods(4, "80010", "不知糖", 32.0, 30.3, 40, 3.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
            , new Goods(5, "80011", "砂糖糖", 42.0, 40.3, 50, 4.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
            , new Goods(6, "80012", "人参糖", 52.0, 50.3, 60, 5.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        initGood();
        kline_root.setVisible(false);
        kline.setVisible(false);

    }

    private void initGood() {
        tb_goods.setItems(datas);
        commNum.setCellValueFactory(data -> data.getValue().commNumProperty());
        commName.setCellValueFactory(data -> data.getValue().commNameProperty());
        openPrice.setCellValueFactory(data -> data.getValue().openPriceProperty().asString());
        newPrice.setCellValueFactory(data -> data.getValue().newPriceProperty().asString());
        upDown.setCellValueFactory(data -> data.getValue().upDownProperty().asString());
        extent.setCellValueFactory(data -> data.getValue().extentProperty().asString());
        maxPrice.setCellValueFactory(data -> data.getValue().maxPriceProperty().asString());
        mixPrice.setCellValueFactory(data -> data.getValue().mixPriceProperty().asString());
        yestedayPrice.setCellValueFactory(data -> data.getValue().yestedayPriceProperty().asString());
        comePrice.setCellValueFactory(data -> data.getValue().comePriceProperty().asString());
        outPrice.setCellValueFactory(data -> data.getValue().outPriceProperty().asString());
        overNumber.setCellValueFactory(data -> data.getValue().overNumberProperty().asString());
        overMoney.setCellValueFactory(data -> data.getValue().overMoneyProperty().asString());

        tb_goods.setRowFactory(tv -> {
            TableRow<Goods> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    Goods Goods = row.getItem();
                    if (event.getClickCount() == 2) {
                        setRowOnTwoClick(Goods);
                    } else if (event.getClickCount() == 1) {
                        setRowOnOneClick(Goods);
                    }

                }
            });
            return row;
        });

    }


    /**
     * 单击设置数据到买入，卖出中
     *
     * @param commodity
     */
    private void setRowOnOneClick(Goods commodity) {

    }

    /**
     * 双击进入分时图
     *
     * @param commodity
     */
    private void setRowOnTwoClick(Goods commodity) {
        goods_root.setVisible(false);
        tb_goods.setVisible(false);
        kline_root.setVisible(true);
        kline.setVisible(true);
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

        SwingUtilities.invokeLater(()->{
            jPanel.setFocusable(true);
            swingNode.setContent(jPanel);
            kline.getChildren().add(swingNode);

            MACD.setStyle(activedCss);
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
                width, height + 40));

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
        jPanel.showPageMinLine();
    }

    public void oneMinuteKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[0]);
    }

    public void fiveMinuteKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[1]);
    }

    public void fifteenMinuteKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[2]);
    }

    public void thrityMinuteKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[3]);
    }

    public void sixtyMinuteKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[4]);
    }

    public void dayKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[5]);
    }

    public void weekKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[6]);
    }

    public void monthKLine(ActionEvent event) {
        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[7]);
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

    public void back(ActionEvent event) {
        goods_root.setVisible(true);
        tb_goods.setVisible(true);
        kline_root.setVisible(false);
        kline.setVisible(false);
    }

    public void MACD(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("MACD");
    }
    public void DMA(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("DMA");
    }
    public void DMI(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("DMI");

    }
    public void OBV(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("OBV");

    }
    public void KDJ(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("KDJ");

    }
    public void BOLL(ActionEvent event) {
        showKline();
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("BOLL");

    }
    public void SAR(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("SAR");

    }
    public void TRIX(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("TRIX");

    }
    public void BRAR(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("BRAR");

    }
    public void VR(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("VR");

    }
    public void EMV(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("EMV");

    }
    public void WR(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("W%R");

    }
    public void ROC(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("ROC");

    }
    public void RSI(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("RSI");

    }
    public void MIKE(ActionEvent event) {
        resetIndicator(event);
        jPanel.getPageMain().changeIndicator("MIKE");

    }

    private void showKline(){
        if(jPanel.iCurrentPage == 1){
            jPanel.showPageKLine();
        }
    }

    private void resetIndicator(ActionEvent event){
        java.util.List<Node> nodeList = Indicator.getChildren();

        for(Node n : nodeList){
            n.setStyle(normalCss);
        }
        Button button = (Button)event.getSource();
        button.setStyle(activedCss);
    }

}
