package controller.center;

import bean.Goods;
import chart.domain.TradeMaketEntity;
import chart.util.StringUtil;
import controller.bottom.NewsController;
import controller.common.Contants;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 15:28 2018/05/28
 * @Modificd :
 */
public class GoodController implements Initializable {
    @FXML
    private AnchorPane goods_root;
    @FXML
    private TableView<Goods> tb_goods;
    @FXML
    private HBox good_type;
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
    private AnchorPane bottom_root;

    private String normalCss ="-fx-background-color: #484848;" +
                    "-fx-background-radius: 0px;" +
                    "-fx-text-fill: #dddddd;" +
                    "-fx-font-size: 12px;" +
                    "-fx-min-width: 40px;" +
                    "-fx-start-margin: 0;" +
                    "-fx-pref-height: 12px;" +
                    "-fx-end-margin: 0;" +
                    "-fx-border-color: #808080;" +
                    "-fx-border-width:0 1px 0 0;" +
                    "-fx-border-style: solid;" +
                    "-fx-cursor: hand;";

    private String activedCss ="-fx-background-color: #D4D0C8;" +
                    "-fx-text-fill: #FF0000;" +
                    "-fx-font-size: 12px;" +
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

    private NewsController newsController;
    private TradeController tradeController;


//    private final ObservableList<Goods> datas = FXCollections.observableArrayList(new Goods(1, "80008", "人参糖", 12.0, 10.3, 30, 0.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
//            , new Goods(2, "80008", "芝麻糖", 12.0, 10.3, 20, 1.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
//            , new Goods(3, "80009", "百度糖", 22.0, 20.3, 30, 2.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
//            , new Goods(4, "80010", "不知糖", 32.0, 30.3, 40, 3.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
//            , new Goods(5, "80011", "砂糖糖", 42.0, 40.3, 50, 4.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00)
//            , new Goods(6, "80012", "人参糖", 52.0, 50.3, 60, 5.12, 0.03, 35.0, 9.5, 20.3, 25.5, 56, 25.6, 33, 80, 23.1, 22.3, 10000, 0.00, 0.00, 0.00));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        initGood();
        initGoodBottom();
    }

    public void setTradeController(TradeController tradeController){
        this.tradeController = tradeController;
    }

    public void setRootVisible(boolean see){
        if(see){
            goods_root.setVisible(true);
        }else {
            goods_root.setVisible(false);
        }
    }

    @FXML
    private void initGoodBottom() {
        ObservableList<Button> buttons = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++) {
            Button button = new Button("海香所" + i);
            if(i==0){
                button.setStyle(activedCss);
            }else {
                button.setStyle(normalCss);
            }
            good_type.getChildren().add(button);
            buttons.add(button);
            button.setOnAction(event -> {
                buttons.forEach(b ->
                        b.setStyle(normalCss)
                );
                button.setStyle(activedCss);
//                if(datas.isEmpty()){
//                    return;
//                }
//                datas.remove(1);
            });
        }
    }

    private void init() {
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


    private void getGood(){
        List<TradeMaketEntity> entities = Contants.getMarketList();
        ObservableList<Goods> markets = FXCollections.observableArrayList();

        for(int i = 0;i < entities.size();i++){
            Goods goods = new Goods();
//            goods.setNumber(i);
            //商品编号 600001
            goods.setCommNum(entities.get(i).getTradeMarket().getName());
            //商品名称
            goods.setCommName(entities.get(i).getTradeMarket().getTitle());
            //开盘价
            goods.setOpenPrice(entities.get(i).getNewest().getOpenPrice().doubleValue());
            //最新价
            goods.setNewPrice(entities.get(i).getNewest().getClosePrice().doubleValue());
            //涨跌
            goods.setUpDown(StringUtil.keepDecimal(entities.get(i).getNewest().getClosePrice().subtract(entities.get(i).getNewest().getOpenPrice()),null));
            //涨跌幅

            //最高价
            goods.setMaxPrice(entities.get(i).getNewest().getMaxPrice().doubleValue());
            //最低价
            goods.setMaxPrice(entities.get(i).getNewest().getMinPrice().doubleValue());
            //昨日收盘价
            goods.setYestedayPrice(entities.get(i).getTradeMarket().getClosePrice().doubleValue());
            //
            goods.setComePrice(entities.get(i).getBuyOne());
            goods.setOutPrice(entities.get(i).getSellOne());
            goods.setOverMoney(entities.get(i).getNewest().getTotalPrice().doubleValue());
            goods.setOverNumber(entities.get(i).getNewest().getTotalNum().intValue());

            markets.add(goods);


        }

        tb_goods.setItems(markets);
    }

    private void initGood() {
        getGood();

        commNum.setCellValueFactory(data -> data.getValue().commNumProperty());
        commNum.setCellFactory(column -> new TableCell<Goods,String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setFont(Font.font("宋体"));
                if(item != null && !empty) {
                    setText(item);
                }else{
                    setText(null);
                }
            }
        });
        commName.setCellValueFactory(data -> data.getValue().commNameProperty());
        commName.setCellFactory(column -> new TableCell<Goods,String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setFont(Font.font("宋体"));
                setText(item);
            }
        });
        openPrice.setCellValueFactory(data -> data.getValue().openPriceProperty().asString());
        newPrice.setCellValueFactory(data -> data.getValue().newPriceProperty().asString());
        upDown.setCellValueFactory(data -> data.getValue().upDownProperty());
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


        Contants.autoFitTable(tb_goods);
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
        tradeController.setRootVisible(true);
    }
}
