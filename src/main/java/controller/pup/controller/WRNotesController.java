package controller.pup.controller;

import bean.Goods;
import bean.WRNotes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.DateUtil;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:53 2018/05/31
 * @Modificd :
 */
public class WRNotesController implements Initializable{
    @FXML
    private DatePicker startTime;
    @FXML
    private DatePicker endTime;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> status;
    @FXML
    private TableView<WRNotes> tvList;
    @FXML
    private TableColumn<WRNotes,String> tvIndex;
    @FXML
    private TableColumn<WRNotes,String> tvSerial;
    @FXML
    private TableColumn<WRNotes,String> tvAmount;
    @FXML
    private TableColumn<WRNotes,String> tvType;
    @FXML
    private TableColumn<WRNotes,String> tvStatus;
    @FXML
    private TableColumn<WRNotes,String> tvTime;
    @FXML
    private TableColumn<WRNotes,String> tvOpera;
    @FXML
    private Pagination pn;
    Stage stage;
    ObservableList<WRNotes> notes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        tvIndex.setCellValueFactory(param -> param.getValue().idProperty().asString());
        tvSerial.setCellValueFactory(param -> param.getValue().serialProperty());
        tvAmount.setCellValueFactory(param -> param.getValue().amountProperty().asString());
        tvType.setCellValueFactory(param -> {
            if(param.getValue().typeProperty().asString().equals("0")){
                return new SimpleStringProperty("出金");
            }
            if(param.getValue().typeProperty().asString().equals("1")){
                return new SimpleStringProperty("入金");
            }
            return new SimpleStringProperty("未知");
        });
        tvStatus.setCellValueFactory(param -> {
            switch (param.getValue().statusProperty().intValue()){
                case 0:
                    return new SimpleStringProperty("申请中");
                case 1:
                    return new SimpleStringProperty("已审核");
                case 2:
                    return new SimpleStringProperty("已完成");
                case 3:
                    return new SimpleStringProperty("已取消");
            }
            return new SimpleStringProperty("未知");
        });
        tvTime.setCellValueFactory(param ->  new SimpleStringProperty(DateUtil.date2String(param.getValue().getCreateTime())));
        tvOpera.setCellValueFactory(param -> {
            if(param.getValue().statusProperty().intValue()==0){
                return new SimpleStringProperty("撤销");
            }
            return null;
        });

        pn.setPageFactory(param -> {
            showList(param);
            return tvList;
        });
    }

    private void showList(Integer param) {
        for (int i=0;i<50;i++){
            WRNotes note = new WRNotes();
            note.setId(i);
            note.setAmount(100+i);
            note.setSerial(String.valueOf(new Date().getTime()+i));
            note.setStatus(Math.round(3));
            note.setType(i/2==0?0:1);
            note.setCreateTime(new Date());
            notes.add(note);
        }
        tvList.setItems(notes);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
