package chart.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TradeDataDesc implements Serializable {

	private static final long serialVersionUID = 5657818675843906805L;

	private BigDecimal maxPrice;

	private BigDecimal minPrice;

	private BigDecimal openPrice;

	private BigDecimal closePrice;

	private BigDecimal totalNum;

	private BigDecimal totalPrice;

	private Date statisticsTime;

	public TradeDataDesc() {
	}

	public TradeDataDesc(Date statisticsTime) {
		super();
		this.maxPrice = new BigDecimal(0);
		this.minPrice = new BigDecimal(0);
		this.openPrice = new BigDecimal(0);
		this.closePrice = new BigDecimal(0);
		this.totalNum = new BigDecimal(0);
		this.statisticsTime = statisticsTime;
		this.totalNum = new BigDecimal(0);
	}



	public TradeDataDesc(String desc) {
//		if (JSUtils.ifStringNotEmpty(desc)) {
//			JSONObject obj = JSONParser.parseObject(desc);
//			if (obj != null) {
//				String maxPrice = obj.getString("maxPrice");
//				this.maxPrice = JSUtils.ifStringEmpty(maxPrice) ? null : new BigDecimal(maxPrice);
//				String minPrice = obj.getString("minPrice");
//				this.minPrice = JSUtils.ifStringEmpty(minPrice) ? null : new BigDecimal(minPrice);
//				String openPrice = obj.getString("openPrice");
//				this.openPrice = JSUtils.ifStringEmpty(openPrice) ? null : new BigDecimal(openPrice);
//				String closePrice = obj.getString("closePrice");
//				this.closePrice = JSUtils.ifStringEmpty(closePrice) ? null : new BigDecimal(closePrice);
//				String totalNum = obj.getString("totalNum");
//				this.totalNum = JSUtils.ifStringEmpty(totalNum) ? null : new BigDecimal(totalNum);
//				String statisticsTime = obj.getString("statisticsTime");
//				this.statisticsTime = JSUtils.ifStringEmpty(statisticsTime) ? null
//						: JSDate.parseDateTime(statisticsTime);
//				String totalPrice = obj.getString("totalPrice");
//				this.totalPrice = JSUtils.ifStringEmpty(totalPrice) ? null : new BigDecimal(totalPrice);
//			}
//		} else {
//			throw new IllegalArgumentException("Invalid data desc.");
//		}
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
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

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	public Date getStatisticsTime() {
		return statisticsTime;
	}

//	@Override
//	public String toString() {
//		StringBuffer s = new StringBuffer();
//		s.append("{");
//		s.append("\"").append("maxPrice").append("\":\"").append(maxPrice == null ? "" : maxPrice.toPlainString())
//				.append("\",");
//		s.append("\"").append("minPrice").append("\":\"").append(minPrice == null ? "" : minPrice.toPlainString())
//				.append("\",");
//		s.append("\"").append("openPrice").append("\":\"").append(openPrice == null ? "" : openPrice.toPlainString())
//				.append("\",");
//		s.append("\"").append("closePrice").append("\":\"").append(closePrice == null ? "" : closePrice.toPlainString())
//				.append("\",");
//		s.append("\"").append("totalNum").append("\":\"").append(totalNum == null ? "" : totalNum.toPlainString())
//				.append("\",");
//		s.append("\"").append("totalPrice").append("\":\"").append(totalPrice == null ? "" : totalPrice.toPlainString())
//				.append("\",");
//		s.append("\"").append("statisticsTime").append("\":\"")
//				.append(statisticsTime == null ? "" : JSDate.formatDateTime(statisticsTime)).append("\"}");
//		return s.toString();
//	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
