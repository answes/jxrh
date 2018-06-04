package bean;

import javafx.beans.property.*;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:43 2018/05/28
 * @Modificd :
 */
public class Goods extends SimpleObjectProperty{
    private String id;
    private StringProperty commNum;
    private StringProperty commName;
    private DoubleProperty openPrice;
    private IntegerProperty comeNumber;
    private DoubleProperty newPrice;
    private StringProperty upDown;
    private DoubleProperty extent;  //  幅度
    private DoubleProperty maxPrice;
    private DoubleProperty mixPrice;
    private DoubleProperty yestedayPrice;
    private DoubleProperty comePrice;
    private DoubleProperty outPrice;
    private IntegerProperty overNumber;
    private DoubleProperty overMoney;

    public Goods() {
        this.commNum =new SimpleStringProperty() ;
        this.commName =new SimpleStringProperty( );
        this.openPrice =new SimpleDoubleProperty( );
        this.newPrice =new SimpleDoubleProperty( );
        this.upDown = new SimpleStringProperty();
        this.extent = new SimpleDoubleProperty();
        this.maxPrice =new SimpleDoubleProperty( );
        this.mixPrice = new SimpleDoubleProperty();
        this.yestedayPrice = new SimpleDoubleProperty();
        this.comePrice =new SimpleDoubleProperty( );
        this.outPrice =new SimpleDoubleProperty( );
        this.overNumber = new SimpleIntegerProperty();
        this.overMoney = new SimpleDoubleProperty();
        this.comeNumber = new SimpleIntegerProperty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpDown() {
        return upDown.get();
    }

    public StringProperty upDownProperty() {
        return upDown;
    }

    public void setUpDown(String upDown) {
        this.upDown.set(upDown);
    }

    public int getComeNumber() {
        return comeNumber.get();
    }

    public IntegerProperty comeNumberProperty() {
        return comeNumber;
    }

    public void setComeNumber(int comeNumber) {
        this.comeNumber.set(comeNumber);
    }

    public String getCommNum() {
        return commNum.get();
    }

    public StringProperty commNumProperty() {
        return commNum;
    }

    public void setCommNum(String commNum) {
        this.commNum.set(commNum);
    }

    public String getCommName() {
        return commName.get();
    }

    public StringProperty commNameProperty() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName.set(commName);
    }

    public double getOpenPrice() {
        return openPrice.get();
    }

    public DoubleProperty openPriceProperty() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice.set(openPrice);
    }

    public double getNewPrice() {
        return newPrice.get();
    }

    public DoubleProperty newPriceProperty() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice.set(newPrice);
    }




    public double getExtent() {
        return extent.get();
    }

    public DoubleProperty extentProperty() {
        return extent;
    }

    public void setExtent(double extent) {
        this.extent.set(extent);
    }

    public double getMaxPrice() {
        return maxPrice.get();
    }

    public DoubleProperty maxPriceProperty() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice.set(maxPrice);
    }

    public double getMixPrice() {
        return mixPrice.get();
    }

    public DoubleProperty mixPriceProperty() {
        return mixPrice;
    }

    public void setMixPrice(double mixPrice) {
        this.mixPrice.set(mixPrice);
    }

    public double getYestedayPrice() {
        return yestedayPrice.get();
    }

    public DoubleProperty yestedayPriceProperty() {
        return yestedayPrice;
    }

    public void setYestedayPrice(double yestedayPrice) {
        this.yestedayPrice.set(yestedayPrice);
    }

    public double getComePrice() {
        return comePrice.get();
    }

    public DoubleProperty comePriceProperty() {
        return comePrice;
    }

    public void setComePrice(double comePrice) {
        this.comePrice.set(comePrice);
    }


    public double getOutPrice() {
        return outPrice.get();
    }

    public DoubleProperty outPriceProperty() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice.set(outPrice);
    }


    public int getOverNumber() {
        return overNumber.get();
    }

    public IntegerProperty overNumberProperty() {
        return overNumber;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber.set(overNumber);
    }

    public double getOverMoney() {
        return overMoney.get();
    }

    public DoubleProperty overMoneyProperty() {
        return overMoney;
    }

    public void setOverMoney(double overMoney) {
        this.overMoney.set(overMoney);
    }




}
