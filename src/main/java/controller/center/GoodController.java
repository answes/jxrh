package controller.center;

import bean.Goods;
import chart.domain.TradeMaketEntity;
import chart.util.StringUtil;
import controller.bottom.NewsController;
import controller.cell.GoodActionCell;
import controller.common.Contants;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    private TableColumn<Goods, String> commAction;
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


    ObservableList<Goods> markets = FXCollections.observableArrayList();
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
            try {
                Button button = new Button(new String(("商品"+i).getBytes(),"utf-8"));
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    private void init() {
        GridPane loader = null;
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

        commAction.setCellValueFactory(data -> data.getValue().commNumProperty());
        commAction.setCellFactory(column -> new GoodActionCell(new GoodActionCell.GoodsActionCellOnClick() {
            @Override
            public void dayLineClick(String item) {
                System.out.println("日点击："+item);
            }

            @Override
            public void klineClick(String item) {
                System.out.println("分点击："+item);
            }
        },markets));

        commNum.setCellValueFactory(data -> data.getValue().commNumProperty());
        commNum.setCellFactory(column -> new TableCell<Goods,String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
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
                    List<Node> nodeList = row.getParent().getChildrenUnmodifiable();
                    for(Node n:nodeList){
                        n.setStyle("-fx-background-color: #000");
                    }
                    row.setStyle("-fx-background-color: #2E2E2E");
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

        tb_goods.getSelectionModel().setCellSelectionEnabled(true);
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
    public void setRowOnTwoClick(Goods commodity) {
        goods_root.setVisible(false);
        tradeController.setRootVisible(true);
    }
}
