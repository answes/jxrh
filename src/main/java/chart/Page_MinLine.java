// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Page_MinLine.java

package chart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import chart.util.HttpUtils;
import chart.util.MinDataVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            Page_Main, MenuListener, HQApplet, ProductData, 
//            SendThread, Draw_MinLine, Draw_Quote, Draw_LastBill

class Page_MinLine extends Page_Main {

    int iProductType;
    String marketId;

    Draw_MinLine draw_MinLine;
    Rectangle rcMinLine;
    int m_iQuoteH;

    void AskForDataOnTimer() {
    }

    /**
     * 请求数据
     */
    void AskForDataOnce() {
        try {
            ReceiveMinLineData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Page_MinLine(Rectangle _rc, HQApplet applet) {
        super(_rc, applet);
        System.out.println("<----Page_MinLine---->");
        m_iQuoteH = 380;
        marketId = applet.marketId;
        AskForDataOnce();
        applet.iCurrentPage = 1;
        draw_MinLine = new Draw_MinLine(applet, true);
        iProductType = applet.getProductType(applet.strCurrentCode);

    }

    void Paint(Graphics g) {
        System.out.println("Page_MinLine--->Paint");
        ProductData stock = super.m_applet.GetProductData(super.m_applet.strCurrentCode);
        g.setFont(new Font("\u6977\u4F53_GB2312", 1, 26));
        FontMetrics fm = g.getFontMetrics();
        g.setFont(new Font("\u5B8B\u4F53", 0, 16));
        fm = g.getFontMetrics();

        rcMinLine = new Rectangle(super.m_rc.x, super.m_rc.y, super.m_rc.width - super.m_rc.x - 10, super.m_rc.height);
        draw_MinLine.Paint(g, rcMinLine, stock);
    }

    private MinDataVO[] GetMinData(){
        HttpUtils httpUtils = new HttpUtils();
        // 58 63 67
        String jsonStr = httpUtils.get("/trade/data/getData.o?marketId="+ marketId +"&dataType=kinelines&limit=500");

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject dataObject = jsonObject.getJSONObject("datas");
        JSONArray retArray = dataObject.getJSONArray("data");

        MinDataVO mins[] = new MinDataVO[retArray.size()];

        SimpleDateFormat format = new SimpleDateFormat("HHmmss");

        float amounts = 0;
        float count = 0;

        for(int i = 0; i < retArray.size(); i++) {

            JSONArray data = retArray.getJSONArray(i);

            count += new BigDecimal(data.get(5).toString()).floatValue();
            amounts += new BigDecimal(data.get(6).toString()).floatValue();

            mins[i] = new MinDataVO();
            mins[i].time = Integer.parseInt(format.format(new Date((long)data.get(0))));
            mins[i].curPrice = new BigDecimal(data.get(4).toString()).floatValue();
            mins[i].totalMoney = new BigDecimal(data.get(5).toString()).floatValue();
            mins[i].totalAmount = new BigDecimal(data.get(5).toString()).longValue();
            mins[i].averPrice = (count == 0 || amounts == 0) ? 0 : new BigDecimal( amounts / count).floatValue();
//            System.out.println("均价:"+mins[i].averPrice+"=="+count+","+amounts);
            mins[i].reserveCount = 0;

        }

        return  mins;
    }
    //接收分时图数据
    private void ReceiveMinLineData() throws IOException {

        //获取分时数据
        MinDataVO values[] = GetMinData();//CMDMinVO.getObj(reader);

        ProductData stock = m_applet.GetProductData(super.m_applet.strCurrentCode);

        m_applet.m_timeRange = stock.m_timeRange;

        if(stock == null) {
            marketId = stock.id;

            if(m_applet.vProductData.size() > 50)
                m_applet.vProductData.removeElementAt(50);
            stock = new ProductData();
            stock.sCode = super.m_applet.strCurrentCode;
            m_applet.vProductData.insertElementAt(stock, 0);
        }
        stock.vMinLine = new Vector();
        int jMin = 0;
        for(int i = 0; i < values.length; i++) {
            int iIndex = Common.GetMinLineIndexFromTime(values[i].time, m_applet.m_timeRange, m_applet.m_iMinLineInterval);
            for(int j = jMin; j < iIndex; j++) {
                MinDataVO min = new MinDataVO();
                if(j > 0) {
                    min.curPrice = ((MinDataVO)stock.vMinLine.elementAt(j - 1)).curPrice;
                    min.totalAmount = ((MinDataVO)stock.vMinLine.elementAt(j - 1)).totalAmount;
                    min.totalMoney = ((MinDataVO)stock.vMinLine.elementAt(j - 1)).totalMoney;
                    min.averPrice = ((MinDataVO)stock.vMinLine.elementAt(j - 1)).averPrice;
                    min.reserveCount = ((MinDataVO)stock.vMinLine.elementAt(j - 1)).reserveCount;
                } else
                if(stock.realData != null) {
                    min.curPrice = stock.realData.yesterBalancePrice;
                    min.averPrice = stock.realData.yesterBalancePrice;
                }
                stock.vMinLine.addElement(min);
            }

            if(iIndex >= stock.vMinLine.size() - 1) {
                MinDataVO min = null;
                if(iIndex == stock.vMinLine.size() - 1) {
                    min = (MinDataVO)stock.vMinLine.lastElement();
                } else {
                    min = new MinDataVO();
                    stock.vMinLine.addElement(min);
                }
                min.curPrice = values[i].curPrice;
                min.totalAmount = values[i].totalAmount;
                min.reserveCount = values[i].reserveCount;
                min.averPrice = values[i].averPrice;
                jMin = iIndex + 1;
            }
        }

        if(1 == m_applet.iCurrentPage && m_applet.strCurrentCode.equals(stock.sCode))
            m_applet.repaint();
    }


    boolean KeyPressed(KeyEvent e) {
        int iKeyCode = e.getKeyCode();

        boolean bResult;
        switch(iKeyCode) {
        case 34: // '"'
            super.m_applet.ChangeStock(false, false);
            bResult = true;
            break;

        case 33: // '!'
            super.m_applet.ChangeStock(true, false);
            bResult = true;
            break;

        default:
            bResult = draw_MinLine.KeyPressed(iKeyCode);
            break;
        }
        return bResult;



    }



    boolean MouseLeftClicked(int x, int y) {
        if(rcMinLine != null && rcMinLine.contains(x, y) && draw_MinLine != null)
            return draw_MinLine.MouseLeftClicked(x, y);
        else
            return false;
    }

    boolean MouseDragged(int x, int y) {
        if(rcMinLine != null && rcMinLine.contains(x, y) && draw_MinLine != null)
            return draw_MinLine.MouseDragged(x, y);
        else
            return false;
    }

    void makeMenus() {
    }

    public void processMenuEvent(PopupMenu popupMenu, int x, int y) {
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.indexOf("cmd_") >= 0) {
            String requestType = cmd.substring(4);
            if(requestType.equals("quote"))
                executeQuoteCommand();
            else
            if(requestType.equals("market"))
                executeMarketCommand();
        } else
        if(cmd.equals("kline"))
            super.m_applet.showPageKLine(super.m_applet.strCurrentCode);
        else
        if(cmd.equals("bill"))
            super.m_applet.UserCommand("01");
        else
        if(cmd.equals("prevstock")) {
            super.m_applet.ChangeStock(true, false);
            super.m_applet.repaint();
        } else
        if(cmd.equals("poststock")) {
            super.m_applet.ChangeStock(false, false);
            super.m_applet.repaint();
        } else {
            super.actionPerformed(e);
        }
    }

    private void executeQuoteCommand() {
        super.m_applet.UserCommand("60");
    }

    private void executeMarketCommand() {
        super.m_applet.UserCommand("80");
    }
}
