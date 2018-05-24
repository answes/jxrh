// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Common.java

package chart;



import chart.util.TradeTimeVO;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            HQApplet, CodeTable

public class Common {

    static final int TYPE_INVALID = -1;
    static final int TYPE_COMMON = 0;
    static final int TYPE_CANCEL = 1;
    static final int TYPE_INDEX = 2;
    static final int TYPE_INDEX_MAIN = 3;
    static final int TYPE_SERIES = 4;
    static final int TYPE_PAUSE = 5;
    static final int TYPE_FINISHIED = 6;
    static final int PRODUCT_CACHENUM = 50;

    public Common() {
    }

    public static int GetProductType(String strCode) {
        return 1;
    }

    public static String FloatToString(double f, int iPrecision) {
        if(iPrecision == 0)
            return String.valueOf((int)f);
        String strTarget = "";
        String strFormat = "0.";
        for(int i = 0; i < iPrecision; i++)
            strFormat = strFormat + "0";

        DecimalFormat dFormat = new DecimalFormat(strFormat);
        strTarget = dFormat.format(f);
        return strTarget;
    }

    public static void DrawDotLine(Graphics g, int x1, int y1, int x2, int y2) {
        int iSpace = 3;
        int iLen = (int)Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        int num = iLen / 3;
        if(num <= 0)
            return;
        for(int i = 0; i < num; i++)
            if(i % 2 == 1)
                g.drawLine(x1 + ((x2 - x1) * i) / num, y1 + ((y2 - y1) * i) / num, x1 + ((x2 - x1) * (i + 1)) / num, y1 + ((y2 - y1) * (i + 1)) / num);

    }

    static String getProductTypeName(int type) {
        switch(type) {
        case 1: // '\001'
            //普通商品
            return "\u666E\u901A\u5546\u54C1";
        }
        return "";
    }

    static int GetTimeFromTimeIndex(int index, TradeTimeVO timeRange[]) {
        if(timeRange == null)
            return 0;
        else
            return TradeTimeVO.GetTimeFromIndex(index, timeRange);
    }

    static int GetTimeIndexFromTime(int hhmm, TradeTimeVO timeRange[]) {
        if(timeRange == null)
            return 0;
        else
            return TradeTimeVO.GetIndexFromTime(hhmm, timeRange);
    }

    static int GetTimeFromMinLineIndex(int index, TradeTimeVO timeRange[], int iMinLineInterval) {
        if(timeRange == null)
            return 0;
        int iNumPerMin = 60 / iMinLineInterval;
        int hhmm = TradeTimeVO.GetTimeFromIndex(index / iNumPerMin, timeRange);
        int ss = (iNumPerMin - 1 - index % iNumPerMin) * iMinLineInterval;
        if(ss > 0) {
            int iMins = ((hhmm / 100) * 60 + hhmm % 100) - 1;
            hhmm = (iMins / 60) * 100 + iMins % 60;
            return hhmm * 100 + (60 - ss);
        } else {
            return hhmm * 100 + ss;
        }
    }

    static int GetMinLineIndexFromTime(int hhmmss, TradeTimeVO timeRange[], int iMinLineInterval) {
        if(timeRange == null)
            return 0;
        int hhmm = hhmmss / 100;
        int ss = hhmmss % 100;
        if(ss > 0) {
            int iMins = (hhmm / 100) * 60 + hhmm % 100;
            hhmm = (++iMins / 60) * 100 + iMins % 60;
        }
        int iIndex = TradeTimeVO.GetIndexFromTime(hhmm, timeRange);
        iIndex *= 60 / iMinLineInterval;
        if(ss == 0)
            ss = 60;
        iIndex += (ss - 1) / iMinLineInterval;
        if(iIndex < 0)
            iIndex = 0;
        return iIndex;
    }

    public static int strlen(Object temp[]) {
        int i;
        for(i = 0; temp[i] != null && i < temp.length; i++);
        return i;
    }

    public static TradeTimeVO[] getTimeRange(String code, HQApplet applet) {
        if(applet.m_timeRange == null)
            return new TradeTimeVO[0];
        if(applet.isIndex(code))
            return applet.m_timeRange;
        CodeTable codeTable = (CodeTable)applet.m_htProduct.get(code);
        if(codeTable == null)
            return new TradeTimeVO[0];
        Vector v = new Vector();
        for(int i = 0; i < codeTable.tradeSecNo.length; i++) {
            for(int j = 0; j < applet.m_timeRange.length; j++)
                if(codeTable.tradeSecNo[i] == applet.m_timeRange[j].orderID)
                    v.addElement(applet.m_timeRange[j]);

        }

        TradeTimeVO dataList[] = new TradeTimeVO[v.size()];
        for(int i = 0; i < dataList.length; i++)
            dataList[i] = (TradeTimeVO)v.elementAt(i);

        return dataList;
    }

    public static int GetCurrent5MinTime(int hhmmss) {
        int newMin = (((hhmmss / 10000) * 60 + (hhmmss / 100) % 100) / 5) * 5;
        if(hhmmss % 500 >= 0)
            newMin += 5;
        int newTime = (newMin / 60) * 10000 + (newMin % 60) * 100;
        return newTime;
    }

    public static String[] split(String str, int ch) {
        Vector v = new Vector();
        int i = 0;
        do {
            int j = str.indexOf(ch, i);
            if(j == -1) {
                v.addElement(str.substring(i));
                break;
            }
            v.addElement(str.substring(i, j));
            i = j + 1;
        } while(true);
        String result[] = new String[v.size()];
        for(i = 0; i < v.size(); i++)
            result[i] = (String)v.elementAt(i);

        return result;
    }

    public static void main(String args[]) {
        ResourceBundle rb = null;
        String s2 = "";
        try {
            rb = ResourceBundle.getBundle("rc/string", new Locale(""));
            String s1 = rb.getString("s2");
            if(s1 != null)
                s2 = new String(s1.getBytes("8859_1"), "GBK");
            else
                System.out.println("null");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("rb:" + rb);
        System.out.println(s2);
    }
}
