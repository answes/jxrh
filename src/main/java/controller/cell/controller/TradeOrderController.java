package controller.cell.controller;

import bean.TradeOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 13:48 2018/06/01
 * @Modificd :
 */
public class TradeOrderController{
    @FXML
    private HBox root;
    @FXML
    private Label lbType,lbPrice,lbNumber,lbAmount;

    public TradeOrderController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/trade_order.fxml"));
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
            switch (order.getIndex()){
                case 0:
                    if(order.getType()==1){
                        lbType.setText("买一");
                    }else if(order.getType()==2){
                        lbType.setText("卖一");
                    }
                    break;
                case 1:
                    if(order.getType()==1){
                        lbType.setText("买二");
                    }else if(order.getType()==2){
                        lbType.setText("卖二");
                    }
                    break;
                case 2:
                    if(order.getType()==1){
                        lbType.setText("买三");
                    }else if(order.getType()==2){
                        lbType.setText("卖三");
                    }
                    break;
                case 3:
                    if(order.getType()==1){
                        lbType.setText("买四");
                    }else if(order.getType()==2){
                        lbType.setText("卖四");
                    }
                    break;
                case 4:
                    if(order.getType()==1){
                        lbType.setText("买五");
                    }else if(order.getType()==2){
                        lbType.setText("卖五");
                    }
                    break;
            }
        if(order.getType()==1){
//            lbType.setText("买一");
            lbType.setTextFill(Color.web("#EB0000"));
        }else if(order.getType()==2){
//            lbType.setText("卖一");
            lbType.setTextFill(Color.web("#00FF00"));
        }
        lbPrice.setText(String.valueOf(order.getPrice()));
        lbNumber.setText(String.valueOf(order.getNumber()));
        lbAmount.setText(String.valueOf(order.getPrice()*order.getNumber()));
    }

    public HBox getRoot(){
        return  root;
    }
}
