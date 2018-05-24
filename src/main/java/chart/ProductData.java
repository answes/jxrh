// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   ProductData.java

package chart;


import chart.util.ProductDataVO;
import chart.util.TradeTimeVO;

import java.util.Vector;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            KLineData

class ProductData {

    String id;
    String sCode;
    ProductDataVO realData;
    Vector vMinLine;
    Vector vBill;
    KLineData dayKLine[];
    KLineData min5KLine[];
    TradeTimeVO m_timeRange[];

    ProductData() {
    }
}
