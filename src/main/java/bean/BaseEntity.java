package bean;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.util.Date;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:29 2018/05/21
 * @Modificd :
 */
public class BaseEntity{
    public LongProperty id;
    public Date createTime;
    public Date updateTime;

    public BaseEntity() {
        this.id = new SimpleLongProperty();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
