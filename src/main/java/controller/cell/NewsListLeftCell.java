package controller.cell;

import bean.News;
import controller.cell.controller.NewsListLeftCellController;
import javafx.scene.control.ListCell;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:46 2018/05/29
 * @Modificd :
 */
public class NewsListLeftCell extends ListCell<News> {
    @Override
    protected void updateItem(News item, boolean empty) {
        super.updateItem(item, empty);
        if(null != item){
            NewsListLeftCellController cotroller = new NewsListLeftCellController();
            cotroller.initData(item.getTilte(), item.getDate().split(" ")[1]);
            setGraphic(cotroller.getRoot());
        }
    }
}
