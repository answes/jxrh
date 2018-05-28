package bean;

import javafx.beans.property.*;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 9:43 2018/05/28
 * @Modificd :
 */
public class Goods {
    private IntegerProperty number;
    private StringProperty commNum;
    private StringProperty commName;
    private DoubleProperty openPrice;
    private DoubleProperty newPrice;
    private IntegerProperty count;
    private DoubleProperty upDown;
    private DoubleProperty extent;  //  幅度
    private DoubleProperty maxPrice;
    private DoubleProperty mixPrice;
    private DoubleProperty yestedayPrice;
    private DoubleProperty comePrice;
    private IntegerProperty comeNumber;
    private DoubleProperty outPrice;
    private IntegerProperty outNumber;
    private IntegerProperty overNumber;
    private DoubleProperty overMoney;
    private DoubleProperty avgPrice;
    private IntegerProperty stockNumber;
    private DoubleProperty numberScale;
    private DoubleProperty trustScale;
    private DoubleProperty exchangeScale;

    public Goods() {

    }

    public Goods(int number, String commNum, String commName, double openPrice, double newPrice, int count, double upDown, double extent, double maxPrice, double mixPrice, double yestedayPrice, double comePrice, int comeNumber, double outPrice, int outNumber, int overNumber, double overMoney, double avgPrice, int stockNumber, double numberScale, double trustScale, double exchangeScale) {
        this.number =new SimpleIntegerProperty(number);
        this.commNum =new SimpleStringProperty(commNum) ;
        this.commName =new SimpleStringProperty( commName);
        this.openPrice =new SimpleDoubleProperty( openPrice);
        this.newPrice =new SimpleDoubleProperty( newPrice);
        this.count = new SimpleIntegerProperty(count);
        this.upDown = new SimpleDoubleProperty(upDown);
        this.extent = new SimpleDoubleProperty(extent);
        this.maxPrice =new SimpleDoubleProperty( maxPrice);
        this.mixPrice = new SimpleDoubleProperty(mixPrice);
        this.yestedayPrice = new SimpleDoubleProperty(yestedayPrice);
        this.comePrice =new SimpleDoubleProperty( comePrice);
        this.comeNumber =new SimpleIntegerProperty( comeNumber);
        this.outPrice =new SimpleDoubleProperty( outPrice);
        this.outNumber = new SimpleIntegerProperty(outNumber);
        this.overNumber = new SimpleIntegerProperty(overNumber);
        this.overMoney = new SimpleDoubleProperty(overMoney);
        this.avgPrice = new SimpleDoubleProperty(avgPrice);
        this.stockNumber = new SimpleIntegerProperty(stockNumber);
        this.numberScale = new SimpleDoubleProperty(numberScale);
        this.trustScale =new SimpleDoubleProperty( trustScale);
        this.exchangeScale =new SimpleDoubleProperty( exchangeScale);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
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

    public int getCount() {
        return count.get();
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public double getUpDown() {
        return upDown.get();
    }

    public DoubleProperty upDownProperty() {
        return upDown;
    }

    public void setUpDown(double upDown) {
        this.upDown.set(upDown);
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

    public int getComeNumber() {
        return comeNumber.get();
    }

    public IntegerProperty comeNumberProperty() {
        return comeNumber;
    }

    public void setComeNumber(int comeNumber) {
        this.comeNumber.set(comeNumber);
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

    public int getOutNumber() {
        return outNumber.get();
    }

    public IntegerProperty outNumberProperty() {
        return outNumber;
    }

    public void setOutNumber(int outNumber) {
        this.outNumber.set(outNumber);
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

    public double getAvgPrice() {
        return avgPrice.get();
    }

    public DoubleProperty avgPriceProperty() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice.set(avgPrice);
    }

    public int getStockNumber() {
        return stockNumber.get();
    }

    public IntegerProperty stockNumberProperty() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber.set(stockNumber);
    }

    public double getNumberScale() {
        return numberScale.get();
    }

    public DoubleProperty numberScaleProperty() {
        return numberScale;
    }

    public void setNumberScale(double numberScale) {
        this.numberScale.set(numberScale);
    }

    public double getTrustScale() {
        return trustScale.get();
    }

    public DoubleProperty trustScaleProperty() {
        return trustScale;
    }

    public void setTrustScale(double trustScale) {
        this.trustScale.set(trustScale);
    }

    public double getExchangeScale() {
        return exchangeScale.get();
    }

    public DoubleProperty exchangeScaleProperty() {
        return exchangeScale;
    }

    public void setExchangeScale(double exchangeScale) {
        this.exchangeScale.set(exchangeScale);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "number=" + number +
                ", commNum='" + commNum + '\'' +
                ", commName='" + commName + '\'' +
                ", openPrice=" + openPrice +
                ", newPrice=" + newPrice +
                ", count=" + count +
                ", upDown='" + upDown + '\'' +
                ", extent='" + extent + '\'' +
                ", maxPrice=" + maxPrice +
                ", mixPrice=" + mixPrice +
                ", yestedayPrice=" + yestedayPrice +
                ", comePrice=" + comePrice +
                ", comeNumber=" + comeNumber +
                ", outPrice=" + outPrice +
                ", outNumber=" + outNumber +
                ", overNumber=" + overNumber +
                ", overMoney=" + overMoney +
                ", avgPrice=" + avgPrice +
                ", stockNumber=" + stockNumber +
                ", numberScale=" + numberScale +
                ", trustScale=" + trustScale +
                ", exchangeScale=" + exchangeScale +
                '}';
    }
}
