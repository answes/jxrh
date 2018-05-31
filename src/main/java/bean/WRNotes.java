package bean;

import javafx.beans.property.*;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 10:53 2018/05/31
 * @Modificd :
 */
public class WRNotes  extends BaseEntity {
    private StringProperty serial;
    private DoubleProperty amount;
    private IntegerProperty type;
    private IntegerProperty status;

    public WRNotes() {
        this.serial = new SimpleStringProperty();
        this.amount = new SimpleDoubleProperty();
        this.type = new SimpleIntegerProperty();
        this.status = new SimpleIntegerProperty();
    }

    public String getSerial() {
        return serial.get();
    }

    public StringProperty serialProperty() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
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

    public int getStatus() {
        return status.get();
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }
}
