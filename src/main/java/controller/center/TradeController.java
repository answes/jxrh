package controller.center;

import bean.TradeOrder;
import chart.Draw_KLine;
import chart.HQApplet;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

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
            tradeOrder.setType(1);
            tradeOrder.setNumber(10);
            tradeOrder.setPrice(10+i);
            sellOrders.add(tradeOrder);
        }
        for(int i=0;i<5;i++){
            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setType(2);
            tradeOrder.setNumber(10);
            tradeOrder.setPrice(10+i);
            buyOrders.add(tradeOrder);
        }

        lvBuys.setCellFactory(param -> new TradeOrderCell(1));
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
}
