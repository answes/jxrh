package chart.domain;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易市场
 */
public class TradeMarket implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4938598930511627768L;

	/**
	 * 买入币种Id
	 */
	private long buyCoinId;
	
	/**
	 * 卖出币种Id
	 */
	private long sellCoinId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 图像资源
	 */
	private String img;
	
	/**
	 * 收盘价
	 */
	private BigDecimal openPrice;
	
	/**
	 * 收盘价
	 */
	private BigDecimal closePrice;
	
	/**
	 * 最大买入，涨停
	 */
	private BigDecimal buyLimitMax;
	
	/**
	 * 最小卖出，跌停
	 */
	private BigDecimal sellLimitMin;
	
	/**
	 * 最小买入价
	 */
	private BigDecimal buyMin;
	
	/**
	 * 最大买入价
	 */
	private BigDecimal buyMax;
	
	/**
	 * 最小卖出价
	 */
	private BigDecimal sellMin;
	
	/**
	 * 最大卖出价
	 */
	private BigDecimal sellMax;
	
	/**
	 * 买入手续费
	 */
	private BigDecimal feeBuy;
	
	/**
	 * 卖出手续费
	 */
	private BigDecimal feeSell;
	
	/**
	 * 单笔交易最小额度
	 */
	private BigDecimal tradeMin;
	
	/**
	 * 单笔交易最大额度
	 */
	private BigDecimal tradeMax;
	
	/**
	 * 单笔最大涨幅
	 */
	private BigDecimal zhang;
	
	/**
	 * 单笔最大跌幅
	 */
	private BigDecimal die;
	
	/**
	 * 交易状态
	 */
	private int trade;
	
	/**
	 * 开盘周期
	 */
	private String tradeWeek;
	
	/**
	 * 今日收盘时间
	 */
	private String tradeTime;

	/**
	 * 发行价
	 */
	private BigDecimal issuPrice;



	/**
	 * 状态
	 */
	private int status;

	/**
	 * 清盘状态 0使用系统配置 1，不使用系统配置，清盘。2不适用系统配置，不清盘。
	 */
	private int cleanState;

	/**
	 *涨停 ，0 使用系统配置，1 涨停后不允许交易 2涨停后允许交易
	 */
	private int limitUp;

	/**
	 * 跌停 ，0 使用系统配置，1 跌停后不允许交易 2跌停后允许交易
	 */
	private int limitDown;

	/**
	 * 锁定日期 0 代表 当日买入当日可卖出
	 */
	private int lockTimer;

	/**
	 * 是否使用流通币结算
	 * 0 使用
	 * 1 不使用
	 */
	private int useSettleCoin;

	/**
	 * 自动
	 */
	private int auto;

	/**
	 * 盈利转结部分 1#百分比 2#固定值
	 * 
	 */
	private String transfer;

	private long businessId;

	//指数市场自动获取数据
	private int autoData;

	//指数市场代码
	private String productCode;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getAutoData() {
		return autoData;
	}

	public void setAutoData(int autoData) {
		this.autoData = autoData;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public long getBuyCoinId() {
		return buyCoinId;
	}

	public void setBuyCoinId(long buyCoinId) {
		this.buyCoinId = buyCoinId;
	}

	public long getSellCoinId() {
		return sellCoinId;
	}

	public void setSellCoinId(long sellCoinId) {
		this.sellCoinId = sellCoinId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getBuyLimitMax() {
		return buyLimitMax;
	}

	public void setBuyLimitMax(BigDecimal buyLimitMax) {
		this.buyLimitMax = buyLimitMax;
	}

	public BigDecimal getSellLimitMin() {
		return sellLimitMin;
	}

	public void setSellLimitMin(BigDecimal sellLimitMin) {
		this.sellLimitMin = sellLimitMin;
	}

	public BigDecimal getBuyMin() {
		return buyMin;
	}

	public void setBuyMin(BigDecimal buyMin) {
		this.buyMin = buyMin;
	}

	public BigDecimal getBuyMax() {
		return buyMax;
	}

	public void setBuyMax(BigDecimal buyMax) {
		this.buyMax = buyMax;
	}

	public BigDecimal getSellMin() {
		return sellMin;
	}

	public void setSellMin(BigDecimal sellMin) {
		this.sellMin = sellMin;
	}

	public BigDecimal getSellMax() {
		return sellMax;
	}

	public void setSellMax(BigDecimal sellMax) {
		this.sellMax = sellMax;
	}

	public BigDecimal getFeeBuy() {
		return feeBuy;
	}

	public void setFeeBuy(BigDecimal feeBuy) {
		this.feeBuy = feeBuy;
	}

	public BigDecimal getFeeSell() {
		return feeSell;
	}

	public void setFeeSell(BigDecimal feeSell) {
		this.feeSell = feeSell;
	}

	public BigDecimal getTradeMin() {
		return tradeMin;
	}

	public void setTradeMin(BigDecimal tradeMin) {
		this.tradeMin = tradeMin;
	}

	public BigDecimal getTradeMax() {
		return tradeMax;
	}

	public void setTradeMax(BigDecimal tradeMax) {
		this.tradeMax = tradeMax;
	}

	public BigDecimal getZhang() {
		return zhang;
	}

	public void setZhang(BigDecimal zhang) {
		this.zhang = zhang;
	}

	public BigDecimal getDie() {
		return die;
	}

	public void setDie(BigDecimal die) {
		this.die = die;
	}

	public int getTrade() {
		return trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public String getTradeWeek() {
		return tradeWeek;
	}

	public void setTradeWeek(String tradeWeek) {
		this.tradeWeek = tradeWeek;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCleanState() {
		return cleanState;
	}

	public void setCleanState(int cleanState) {
		this.cleanState = cleanState;
	}

	public int getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(int limitUp) {
		this.limitUp = limitUp;
	}

	public int getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(int limitDown) {
		this.limitDown = limitDown;
	}

	public BigDecimal getIssuPrice() {
		return issuPrice;
	}

	public void setIssuPrice(BigDecimal issuPrice) {
		this.issuPrice = issuPrice;
	}

	public int getLockTimer() {
		return lockTimer;
	}

	public void setLockTimer(int lockTimer) {
		this.lockTimer = lockTimer;
	}

	public int getUseSettleCoin() {
		return useSettleCoin;
	}

	public void setUseSettleCoin(int useSettleCoin) {
		this.useSettleCoin = useSettleCoin;
	}

	public int getAuto() {
		return auto;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
}
