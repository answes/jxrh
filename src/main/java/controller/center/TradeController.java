package controller.center;

import bean.Goods;
import bean.TradeOrder;
import chart.Draw_KLine;
import chart.HQApplet;
import chart.util.StringUtil;
import controller.bottom.OperController;
import controller.cell.TradeOrderCell;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Paint;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 15:21 2018/05/28
 * @Modificd :
 */
public class TradeController implements Initializable {

    @FXML
    private HBox Indicator;
    @FXML
    private Button MACD;
    @FXML
    private HQApplet jPanel = new HQApplet(0);
    @FXML
    private SwingNode swingNode = new SwingNode();
    @FXML
    private AnchorPane view_root;
    @FXML
    private HBox rightBox;
    @FXML
    private int width;
    @FXML
    private int height;
    @FXML
    private AnchorPane bottom_root;
    @FXML
    private Pane kline;
    @FXML
    private ListView<TradeOrder> lvSells;
    @FXML
    private ListView<TradeOrder> lvBuys;
    @FXML
    private ListView<TradeOrder> lvTrade;
    private OperController operController;

    @FXML
    javafx.scene.control.Label cus;

    @FXML
    javafx.scene.control.Label currentPrice;
    @FXML
    javafx.scene.control.Label changeRadio;
    @FXML
    javafx.scene.control.Label changeValue;
    @FXML
    javafx.scene.control.Label yestodayClose;
    @FXML
    javafx.scene.control.Label count;
    @FXML
    javafx.scene.control.Label open;
    @FXML
    javafx.scene.control.Label amount;
    @FXML
    javafx.scene.control.Label high;
    @FXML
    javafx.scene.control.Label change;
    @FXML
    javafx.scene.control.Label low;

    private String normalCss =
            "-fx-background-color: #484848;" +
                    "-fx-background-radius: 0px;" +
                    "-fx-text-fill: #dddddd;" +
                    "-fx-font-size: 10px;" +
                    "-fx-padding: 1px 4px;" +
                    "-fx-min-width: 40px;" +
                    "-fx-start-margin: 0;" +
                    "-fx-pref-height: 12px;" +
                    "-fx-end-margin: 0;" +
                    "-fx-border-color: #808080;" +
                    "-fx-border-width:0 1px 0 0;" +
                    "-fx-border-style: solid;" +
                    "-fx-cursor: hand;";

    private String activedCss =
            "-fx-background-color: #D4D0C8;" +
                    "-fx-text-fill: #FF0000;" +
                    "-fx-font-size: 10px;" +
                    "-fx-padding: 1px 4px;" +
                    "-fx-border-width:0 1px 0 0;" +
                    "-fx-start-margin: 0;" +
                    "-fx-end-margin: 0;" +
                    "-fx-border-style: solid;" +
                    "-fx-border-color: #808080;" +
                    "-fx-border-radius: 0px;" +
                    "-fx-background-radius: 0px;" +
                    "-fx-cursor: hand;" +
                    "-fx-fill-height: true;" +
                    "-fx-pref-height: 12px;" +
                    "-fx-max-height: 12px;";
    @FXML
    private AnchorPane kline_root;

    /**
     * 当前商品
     */
    private Goods currentGoods;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    public void setLock(){
        operController.setLock();
    }
    public boolean isLock(){
        return operController.isLock();
    }

    public void setRootVisible(boolean see){
        if(see){
            view_root.setVisible(true);
        }else {
            view_root.setVisible(false);
        }
    }

    private void init() {
        SwingUtilities.invokeLater(() -> {
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

        loadTrade();
        initTrade();
    }

    private void initTrade() {
        ObservableList<TradeOrder> sellOrders = FXCollections.observableArrayList();
        ObservableList<TradeOrder> buyOrders = FXCollections.observableArrayList();
        for(int i=0;i<5;i++){
            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setIndex(i);
            tradeOrder.setType(1);
            tradeOrder.setNumber(10);
            tradeOrder.setPrice(10+i);
            sellOrders.add(tradeOrder);
        }
        for(int i=0;i<5;i++){
            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setIndex(4-i);
            tradeOrder.setType(2);
            tradeOrder.setNumber(10);
            tradeOrder.setPrice(10-i);
            buyOrders.add(tradeOrder);
        }

        lvBuys.setCellFactory((ListView<TradeOrder> l) -> new TradeOrderCell(1));
        lvSells.setCellFactory(param -> new TradeOrderCell(1));
        lvTrade.setCellFactory(param -> new TradeOrderCell(2));
        lvBuys.setItems(sellOrders);
        lvSells.setItems(buyOrders);
        lvTrade.setItems(buyOrders);


    }
    private void loadTrade() {
        AnchorPane loader = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/oper.fxml"));
        try {
            loader = fxmlLoader.load();
            //这个是我把获取MainBottomController添加到这个Controller的底下布局，你不用管
            bottom_root.getChildren().add(loader);
            //获取MainBottomController
            operController = fxmlLoader.getController();
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

    public void showMinLine() {
        jPanel.showPageMinLine();
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

    public void cycle(int i) {

        showKline();
        jPanel.getPageMain().changeCycle(Draw_KLine.indicators[i]);
    }

    public void setCurrentGood(Goods goods){
        if(goods == null){
            return;
        }
        currentGoods = goods;
        jPanel.marketId = currentGoods.getId();

        setGoodsInfo(currentGoods);
    }

    public void setLineType(int lineType){
        jPanel.iCurrentPage = lineType;
    }

    private void setGoodsInfo(Goods goods){
        cus.setText(goods.getCommName());

        //最新价
        currentPrice.setText(String.valueOf(goods.getNewPrice()));

        double changeRadioD = StringUtil.calcChangeRadio(goods);
        double changeValueD = StringUtil.calcChangeValue(goods);
        double changeD = StringUtil.calcChange(goods);

        //涨跌幅
        if(changeRadioD > 0){
            changeRadio.setTextFill(Paint.valueOf("#EB0000"));
            changeValue.setTextFill(Paint.valueOf("#EB0000"));
        }else if(changeRadioD < 0){
            changeRadio.setTextFill(Paint.valueOf("#00DB00"));
            changeValue.setTextFill(Paint.valueOf("#00DB00"));
        }else{
            changeRadio.setTextFill(Paint.valueOf("#FFFFFF"));
            changeValue.setTextFill(Paint.valueOf("#FFFFFF"));
        }

        changeRadio.setText(StringUtil.keepDecimal(changeRadioD,null)+"%");
        //涨跌值
        changeValue.setText(StringUtil.keepDecimal(changeValueD,null));

        if(changeD > 0){
            change.setTextFill(Paint.valueOf("#EB0000"));
        }else if(changeD < 0){
            change.setTextFill(Paint.valueOf("#00DB00"));
        }else{
            change.setTextFill(Paint.valueOf("#FFFFFF"));
        }

        //振幅
        change.setText(StringUtil.keepDecimal(changeD,null) +"%");
        //收盘价
        yestodayClose.setText(StringUtil.keepDecimal(goods.getYestedayPrice(),null));
        //总量
        count.setText(calcNumber(goods.getOverNumber()));
        //总金额
        amount.setText(calcNumber(goods.getOverMoney()));
        //开盘价
        open.setText(StringUtil.keepDecimal(goods.getOpenPrice(),null));
        //高
        high.setText(StringUtil.keepDecimal(goods.getMaxPrice(),null));
        //低
        low.setText(StringUtil.keepDecimal(goods.getMixPrice(),null));

    }

    private String calcNumber(double n){
        if(n > 10000){
            return StringUtil.keepDecimal((n / 10000),null) + "万";
        }else if(n > 10000 * 10000){
            return StringUtil.keepDecimal((n / (10000 * 10000)),null) + "亿";
        }else{
            return  StringUtil.keepDecimal(n,null);
        }

    }

    public void changeGoods(Goods goods){
        if(goods == null){
            return;
        }
        setCurrentGood(goods);
        jPanel.QueryStock(goods.getCommNum());

    }
}
