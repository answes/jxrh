package controller.cell;

import bean.News;
import controller.cell.controller.CenterNewsListCellController;
import javafx.scene.control.ListCell;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 10:13 2018/05/30
 * @Modificd :
 */
public class CenterNewsCell extends ListCell<News> {
    @Override
    protected void updateItem(News item, boolean empty) {
        super.updateItem(item, empty);
        if(null != item){
            CenterNewsListCellController controller = new CenterNewsListCellController();
            controller.initData(item.getTilte(), item.getDate().split(" ")[1]);
            setGraphic(controller.getRoot());
        }
    }
}
