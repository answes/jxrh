package controller.cell.controller;

import bean.TradeOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utils.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 13:48 2018/06/01
 * @Modificd :
 */
public class TransactionController {
    @FXML
    private HBox root;
    @FXML
    private Label lbTime,lbPrice,lbNumber;

    public TransactionController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/transaction_item.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void initData(TradeOrder order,int index){
        if(order.getType()==1){
            lbTime.setTextFill(Color.web("#991E10"));
            lbPrice.setTextFill(Color.web("#991E10"));
            lbNumber.setTextFill(Color.web("#991E10"));
        }else if(order.getType()==2){
            lbTime.setTextFill(Color.web("#22bd69"));
            lbPrice.setTextFill(Color.web("#22bd69"));
            lbNumber.setTextFill(Color.web("#22bd69"));
        }
        lbTime.setText(DateUtil.getNowTime());
        lbPrice.setText(String.valueOf(order.getPrice()));
        lbNumber.setText(String.valueOf(order.getNumber()));
    }

    public HBox getRoot(){
        return  root;
    }
}
