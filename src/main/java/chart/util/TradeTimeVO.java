// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:40:59 

//source File Name:   TradeTimeVO.java

package chart.util;

import java.util.Date;

/**
 * 交易时间段
 */
public class TradeTimeVO {

    public int orderID;
    public int beginTime;
    public int endTime;
    public int status;
    public Date modifytime;

    public TradeTimeVO() {
        modifytime = new Date();
    }

    public String toString() {
        String sep = "\n";
        StringBuffer sb = new StringBuffer();
        sb.append("**" + getClass().getName() + "**" + sep);
        sb.append("OrderID:" + orderID + sep);
        sb.append("BeginTime:" + beginTime + sep);
        sb.append("EndTime:" + endTime + sep);
        sb.append("Status:" + status + sep);
        sb.append("Modifytime:" + modifytime + sep);
        sb.append(sep);
        return sb.toString();
    }

    public static String timeIntToString(int iTime) {
        return iTime / 100 + ":" + iTime % 100;
    }

    public static int timeStringToInt(String strTime) {
        strTime.replaceAll(":", "");
        return Integer.parseInt(strTime);
    }

    public static int GetTotalMinute(TradeTimeVO timeRange[]) {
        int iMin = 0;
        for(int i = 0; i < timeRange.length; i++)
            iMin += ((timeRange[i].endTime / 100) * 60 + timeRange[i].endTime % 100) - ((timeRange[i].beginTime / 100) * 60 + timeRange[i].beginTime % 100);

        return iMin;
    }

    public static int GetTimeFromIndex(int iIndex, TradeTimeVO timeRange[]) {
        int iIndexCur = iIndex + 1;
        for(int i = 0; i < timeRange.length; i++) {
            int iRange = ((timeRange[i].endTime / 100) * 60 + timeRange[i].endTime % 100) - ((timeRange[i].beginTime / 100) * 60 + timeRange[i].beginTime % 100);
            if(iRange < iIndexCur) {
                iIndexCur -= iRange;
            } else {
                int iTime = (timeRange[i].beginTime / 100) * 60 + timeRange[i].beginTime % 100 + iIndexCur;
                iTime = (iTime / 60) * 100 + iTime % 60;
                return iTime;
            }
        }

        return -1;
    }

    public static int GetIndexFromTime(int iTime, TradeTimeVO timeRange[]) {
        int iIndex = -1;
        for(int i = 0; i < timeRange.length; i++) {
            if(iTime < timeRange[i].beginTime)
                return iIndex;
            if(timeRange[i].endTime >= iTime && iTime >= timeRange[i].beginTime)
                iIndex += ((iTime / 100) * 60 + iTime % 100) - ((timeRange[i].beginTime / 100) * 60 + timeRange[i].beginTime % 100);
            else
                iIndex += ((timeRange[i].endTime / 100) * 60 + timeRange[i].endTime % 100) - ((timeRange[i].beginTime / 100) * 60 + timeRange[i].beginTime % 100);
        }

        if(iIndex < 0)
            iIndex = 0;
        return iIndex;
    }

    public static void main(String arg[]) {
        TradeTimeVO vo[] = new TradeTimeVO[1];
        vo[0] = new TradeTimeVO();
        vo[0].beginTime = 930;
        vo[0].endTime = 1800;
        vo[0].orderID = 0;
        System.out.println(GetIndexFromTime(931, vo));
        System.out.println(GetTimeFromIndex(0, vo));
    }
}
