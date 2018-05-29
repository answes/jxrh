package controller.cell;

import bean.News;
import controller.cell.controller.NewsListCellController;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 10:01 2018/05/29
 * @Modificd :
 */
public class NewsListCell extends ListCell<News> {
    @Override
    protected void updateItem(News item, boolean empty) {
        super.updateItem(item, empty);
        if(null != item) {
            NewsListCellController cotroller = new NewsListCellController();
            cotroller.initData(item.getTilte(), item.getDate());
            setGraphic(cotroller.getRoot());
        }
    }
}
