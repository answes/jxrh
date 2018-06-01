package bean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 13:37 2018/06/01
 * @Modificd :
 */
public class TradeOrder extends BaseEntity {
    private IntegerProperty index;
    private IntegerProperty type;
    private DoubleProperty price;
    private DoubleProperty number;

    public TradeOrder() {
        this.index = new SimpleIntegerProperty();
        this.type = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.number = new SimpleDoubleProperty();
    }

    public int getIndex() {
        return index.get();
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public int getType() {
        return type.get();
    }

    public IntegerProperty typeProperty() {
        return type;
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getNumber() {
        return number.get();
    }

    public DoubleProperty numberProperty() {
        return number;
    }

    public void setNumber(double number) {
        this.number.set(number);
    }
}
