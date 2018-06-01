package controller.cell;

import bean.TradeOrder;
import controller.cell.controller.TradeOrderController;
import controller.cell.controller.TransactionController;
import javafx.scene.control.ListCell;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 13:37 2018/06/01
 * @Modificd :
 */
public class TradeOrderCell extends ListCell<TradeOrder> {
    private int type;

    public TradeOrderCell(int type) {
        this.type = type;
    }

    @Override
    protected void updateItem(TradeOrder item, boolean empty) {
        super.updateItem(item, empty);
        if(null != item){
            if(type==1){
                TradeOrderController tradeOrderController = new TradeOrderController();
                tradeOrderController.initData(item,1);
                setGraphic(tradeOrderController.getRoot());
            }else if(type==2){
                TransactionController transactionController = new TransactionController();
                transactionController.initData(item,1);
                setGraphic(transactionController.getRoot());
            }
        }
    }
}
