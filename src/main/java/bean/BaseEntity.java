package bean;

import java.util.Date;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 14:29 2018/05/21
 * @Modificd :
 */
public class BaseEntity{
    public Long id;
    public Date createTime;
    public Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
