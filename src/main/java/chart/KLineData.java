// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   KLineData.java

package chart;

/**
 * K线数据
 */
public class KLineData {

    /**
     * 日期
     */
    public long date;
    /**
     * 开
     */
    public float openPrice;
    /**
     * 收
     */
    public float closePrice;
    /**
     * 高
     */
    public float highPrice;
    /**
     * 低
     */
    public float lowPrice;
    /**
     * 当前价格
     */
    public float balancePrice;
    /**
     * 总量
     */
    public long totalAmount;
    /**
     * 总金额
     */
    public double totalMoney;
    /**
     *
     */
    public int reserveCount;

    public KLineData() {
    }

    public String toString() {
        return "\r\ndate:" + date + "\r\nopenPrice:" + openPrice + "\r\nhighPrice:" + highPrice + "\r\nlowPrice:" + lowPrice + "\r\nclosePrice:" + closePrice + "\r\ntotalAmount:" + totalAmount + "\r\ntotalMoney:" + totalMoney;
    }
}
