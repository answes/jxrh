package chart.domain;


/**
 * 返回信息
 * 1、市场名称
 * 2、市场
 * 3、交易名称
 * 4、交易数据
 */
public class TradeMaketEntity {
    private TradeMarket tradeMarket;
    private TradeDataDesc newest;
    private double sellOne;
    private double buyOne;

    public double getSellOne() {
        return sellOne;
    }

    public void setSellOne(double sellOne) {
        this.sellOne = sellOne;
    }

    public double getBuyOne() {
        return buyOne;
    }

    public void setBuyOne(double buyOne) {
        this.buyOne = buyOne;
    }

    public TradeMarket getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(TradeMarket tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public TradeDataDesc getNewest() {
        return newest;
    }

    public void setNewest(TradeDataDesc newest) {
        this.newest = newest;
    }
}
