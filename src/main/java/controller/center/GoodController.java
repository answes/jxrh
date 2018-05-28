package controller.center;

import bean.Goods;
import controller.bottom.NewsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
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

    private NewsController newsController;
    private TradeController tradeController;


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
        tradeController.setRootVisible(true);
    }
}
