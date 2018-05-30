package controller.cell;

import bean.Goods;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by 京美电子 on 2018/5/29.
 */
public class GoodActionCell extends TableCell<Goods, String> {
    // a button for adding a new person.
    final Button addButton       = new Button("日");
    final Button removeButton       = new Button("分");
    final HBox hBox = new HBox();
    // pads and centers the add button in the cell.
    final StackPane paddedButton = new StackPane();
    // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
    final DoubleProperty buttonY = new SimpleDoubleProperty();
    GoodsActionCellOnClick goodsActionCellOnClick;

    String text = "-1";

    final String css =
            "-fx-font-size: 12px;"+
            "-fx-pref-height:13px;" +
            "-fx-background-color:#00FFFF;"+
            "-fx-text-fill: #000;"+
            "-fx-border-width: 0 1 0 0;"+
            "-fx-border-style: solid;"+
            "-fx-border-color: #000;" +
            "-fx-background-radius: 1;" +
            "-fx-font-weight:bold;"+
            "-fx-padding:1 2";

    /**
     * AddPersonCell constructor
     * @param table the table to which a new person can be added.
     */
    public GoodActionCell(GoodsActionCellOnClick goodsActionCellOnClick) {
        addButton.setStyle(css);
        removeButton.setStyle(css);
        hBox.getChildren().add(addButton);
        hBox.getChildren().add(removeButton);
        hBox.setStyle("-fx-alignment: center");
        hBox.setPadding(new Insets(3,0,0,0));

        String str = this.getTableColumn() == null ? "-1" : this.getTableColumn().textProperty().getValue();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goodsActionCellOnClick.dayLineClick(text);
            }
        });
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goodsActionCellOnClick.klineClick(text);
            }
        });
    }

    /** places an add button in the row only if the row is not empty. */
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            text = item;
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(hBox);
        } else {
            setGraphic(null);
        }
    }

    public interface GoodsActionCellOnClick{
        void dayLineClick(String item);
        void klineClick(String item);
    }
}
