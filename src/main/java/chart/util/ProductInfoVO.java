// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:40:59 

//source File Name:   ProductInfoVO.java

package chart.util;

import java.util.Date;

public class ProductInfoVO {

    public String code;
    public String name;
    public int type;
    public int openTime;
    public int tradeSecNo[];
    public int closeTime;
    public Date modifyTime;
    public static final int TYPE_INVALID = -1;
    public static final int TYPE_COMMON = 0;
    public static final int TYPE_CANCEL = 1;
    public static final int TYPE_INDEX = 2;
    public static final int TYPE_INDEX_MAIN = 3;
    public static final int TYPE_SERIES = 4;
    public static final int TYPE_PAUSE = 5;
    public static final int TYPE_FINISHIED = 6;
    public int status;
    public String pyName[];
    public float fUnit;

    public ProductInfoVO() {
        tradeSecNo = new int[1];
        pyName = new String[0];
        fUnit = 1.0F;
    }

    public String toString() {
        String sep = "\n";
        StringBuffer sb = new StringBuffer();
        sb.append("**" + getClass().getName() + "**" + sep);
        sb.append("Code:" + code + sep);
        sb.append("Name:" + name + sep);
        sb.append("Type:" + type + sep);
        sb.append("CloseTime:" + closeTime + sep);
        sb.append("CreateTime:" + openTime + sep);
        sb.append("ModifyTime:" + modifyTime + sep);
        sb.append("Status:" + status + sep);
        sb.append("pyName.length:" + pyName.length + sep);
        for(int i = 0; i < pyName.length; i++)
            sb.append("pyName[" + i + "]:" + pyName[i] + sep);

        sb.append(sep);
        return sb.toString() + super.toString();
    }
}
