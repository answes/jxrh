// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Page_KLine.java

package chart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import chart.util.HttpUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            Page_Main, MenuListener, HQApplet, Draw_KLine, 
//            Draw_MinLine, ProductData, SendThread, Packet_HttpRequest, 
//            HttpThread, Draw_Quote

/**
 * K线右键菜单按钮
 */
public class Page_KLine extends Page_Main {

    static int m_iCurKLineType = 0;
    int iProductType;
    Draw_KLine draw_KLine;
    /**
     * 分时图
     */
//    Draw_MinLine draw_MinLine;
    /**
     * 绘图区域
      */
    Rectangle rcKLine;
    /**
     * oneday
     * fifteenminutes
     * 请求K线类型
     */
    String lineType = "fifteenminutes";
    String marketId;

    public Page_KLine(Rectangle _rc, HQApplet applet) {
        super(_rc, applet);
        marketId = applet.marketId;
        System.out.println("Rectangle:"+ _rc.getWidth() +":"+_rc.getHeight());
        AskForDataOnce();
        super.m_applet.iCurrentPage = 2;
        // 绘制k线图
        draw_KLine = new Draw_KLine(this);

        // 产品类型
        iProductType = super.m_applet.getProductType(super.m_applet.strCurrentCode);
    }

    void AskForDataOnTimer() {

    }

    void AskForDataOnce() {

        AskForKLine();

    }

    /**
     * 测试K线数据
     */
    private KLineData[] GetKLineData(){

        HttpUtils httpUtils = new HttpUtils();
        // 58 63 67
        String jsonStr = httpUtils.get("/trade/data/getData.o?marketId=" + marketId +"&dataType="+ lineType +"&limit=500");

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject dataObject = jsonObject.getJSONObject("datas");
        JSONArray retArray = dataObject.getJSONArray("data");

        KLineData hisStatus[] = new KLineData[retArray.size()];

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        for(int i = 0;i < retArray.size();i ++){
            KLineData data = new KLineData();

            JSONArray d = retArray.getJSONArray(i);

            Date date = new Date();
            date.setTime((long)d.get(0));

            data.date = Long.parseLong(format.format(date));
            data.openPrice =  new BigDecimal(d.get(1).toString()).floatValue();
            data.highPrice =  new BigDecimal(d.get(2).toString()).floatValue();
            data.lowPrice =  new BigDecimal(d.get(3).toString()).floatValue();
            data.closePrice =  new BigDecimal(d.get(4).toString()).floatValue() ;

            data.totalAmount =  new BigDecimal(d.get(5).toString()).longValue();
            data.totalMoney =  new BigDecimal(d.get(6).toString()).floatValue();
            data.reserveCount = -1;

            hisStatus[i] = data;
        }

        return hisStatus;
    }

    /**
     * 获取日线数据
     */
    private void GetKLine(Packet_HttpRequest request) {
        System.out.println("Page_KLine-->GetDayLine");
        try {
            //数据请求连接
            //获取到的K线数据
            KLineData hisStatus[] = GetKLineData();
            //getHistoryData(strURL);
            //根据产品编码获取产品信息
            ProductData product = m_applet.GetProductData(request.sCode);
            /**
             * 产品不存在时，新增产品信息
             */
            if(product == null) {
                if(m_applet.vProductData.size() > 50)
                    m_applet.vProductData.removeElementAt(50);
                product = new ProductData();
                product.sCode = request.sCode;
                product.dayKLine = hisStatus;
                m_applet.vProductData.insertElementAt(product, 0);
            } else {
                // 产品存在，设置日线数据
                product.dayKLine = hisStatus;
            }

            // K线数据存在时
            if(hisStatus.length > 0) {
//                HQApplet _tmp1 = m_applet;
                //m_applet.iCurrentPage:1分时图，2k线图
                if(2 == m_applet.iCurrentPage && m_applet.strCurrentCode.equals(request.sCode))
                    m_applet.repaint();
            }
        }

        catch(Exception ex) {
//            HQApplet _tmp4 = m_applet;
            if(HQApplet.bDebug != 0)
                ex.printStackTrace();
        }
    }

    /**
     * 请求K线数据
     */
    void AskForKLine() {
        //获取当前产品
        ProductData stock = super.m_applet.GetProductData(super.m_applet.strCurrentCode);
        if(stock != null)
            //设置市场Id
            marketId = stock.id;
            if(1 == super.m_applet.m_iKLineCycle || 2 == super.m_applet.m_iKLineCycle || 3 == super.m_applet.m_iKLineCycle) {
                if(stock.dayKLine != null)
                    return;
            } else if(stock.min5KLine != null)
                return;
        Packet_HttpRequest request = new Packet_HttpRequest();
        request.sCode = super.m_applet.strCurrentCode;
        if(1 == super.m_applet.m_iKLineCycle || 2 == super.m_applet.m_iKLineCycle || 3 == super.m_applet.m_iKLineCycle)
            request.type = 0;
        else
            request.type = 1;
        GetKLine(request);
    }


    void Paint(Graphics g) {
        System.out.println("Page_KLine--->Paint");
        ProductData stock = super.m_applet.GetProductData(super.m_applet.strCurrentCode);
        g.setFont(new Font("\u6977\u4F53_GB2312", 1, 26));
        g.setFont(new Font("\u5B8B\u4F53", 0, 16));
        //控制K线绘图区域 new Rectangle(x坐标，y坐标，宽度，高度);
        rcKLine = new Rectangle(super.m_rc.x - 62, super.m_rc.y + 1 , super.m_rc.width + 15 , super.m_rc.height);
        //在指定区域内绘制K线
        draw_KLine.Paint(g, rcKLine, stock);
    }

    /**
     * 修改显示的产品
     * @param e
     * @return
     */
    boolean KeyPressed(KeyEvent e) {
        int iKeyCode = e.getKeyCode();
        boolean bResult;
        switch(iKeyCode) {
        case 34: // '"'
            super.m_applet.ChangeStock(false, true);
            bResult = true;
            break;

        case 33: // '!'
            super.m_applet.ChangeStock(true, true);
            bResult = true;
            break;

        default:
            bResult = draw_KLine.KeyPressed(e);
            break;
        }
        return bResult;
    }

    boolean MouseLeftClicked(int x, int y) {
        if(rcKLine != null && rcKLine.contains(x, y) && draw_KLine != null)
            return draw_KLine.MouseLeftClicked(x, y);
        else
            return false;
    }

    boolean MouseDragged(int x, int y) {
        if(rcKLine != null && rcKLine.contains(x, y) && draw_KLine != null)
            return draw_KLine.MouseDragged(x, y);
        else
            return false;
    }

    void makeMenus() {

    }

    void processMenuEvent(PopupMenu popupMenu, int x, int y) {
    }

    void setMenuEnable(String label, boolean b) {

    }

    void setIndicatorSubMenusAllTrue() {

    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if("USA".equals(cmd)) {
            m_iCurKLineType = 1;
            draw_KLine.ChangeKLineType(1);
            return;
        }
        if("KLine".equals(cmd)) {
            m_iCurKLineType = 0;
            draw_KLine.ChangeKLineType(0);
            return;
        }
        if("Poly".equals(cmd)) {
            m_iCurKLineType = 2;
            draw_KLine.ChangeKLineType(2);
            return;
        }
        if(cmd.indexOf("cmd_") >= 0) {
            String requestType = cmd.substring(4);
            if(requestType.equals("quote")) {
                executeQuoteCommand();
                return;
            }
            if(requestType.equals("market")) {
                executeMarketCommand();
                return;
            }
        } else {
            if(cmd.equals("zoomin")) {
                zoomInKLineGraph();
                return;
            }
            if(cmd.equals("zoomout")) {
                zoomOutKLineGraph();
                return;
            }
            if(cmd.equals("prevstock")) {
                super.m_applet.ChangeStock(true, true);
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("poststock")) {
                super.m_applet.ChangeStock(false, true);
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("day")) {
                super.m_applet.m_iKLineCycle = 1;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("week")) {
                super.m_applet.m_iKLineCycle = 2;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("month")) {
                super.m_applet.m_iKLineCycle = 3;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("min5")) {
                super.m_applet.m_iKLineCycle = 4;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("min15")) {
                super.m_applet.m_iKLineCycle = 5;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("min30")) {
                super.m_applet.m_iKLineCycle = 6;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("min60")) {
                super.m_applet.m_iKLineCycle = 7;
                AskForKLine();
                super.m_applet.repaint();
                return;
            }
            if(cmd.equals("minline")) {
                super.m_applet.showPageMinLine();
                return;
            }
            if(cmd.equals("bill")) {
                super.m_applet.UserCommand("01");
                return;
            }
            // 修改指标类型
            if(cmd.startsWith("Indicator_")) {
                super.m_applet.m_strIndicator = cmd.substring(10);
                draw_KLine.CreateIndicator();
                super.m_applet.repaint();
            } else {
                super.actionPerformed(e);
            }
        }
    }

    /**
     * 修改K线周期，
     * @param cycle
     */

    @Override
    public void changeCycle(String cycle){
        lineType = cycle;
        System.out.println(this.getClass().getName()+":"+cycle);
        switch(cycle){
            //日线
            case "oneday":
                super.m_applet.m_iKLineCycle = 1;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //周线
            case "sevenday":
                super.m_applet.m_iKLineCycle = 2;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //月线
            case "onemonth":
                super.m_applet.m_iKLineCycle = 3;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //5分线
            case "fiveminutes":
                super.m_applet.m_iKLineCycle = 4;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //15分线
            case "fifteenminutes":
                super.m_applet.m_iKLineCycle = 5;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //30分线
            case "thirtyminutes":
                super.m_applet.m_iKLineCycle = 6;
                AskForKLine();
                super.m_applet.repaint();
                break;
            //小时线
            case "onehours":
                super.m_applet.m_iKLineCycle = 7;
                AskForKLine();
                super.m_applet.repaint();
                break;
        }

    }

    /**
     * 修改指标
     * @param type 指标类型
     */
    @Override
    public void changeIndicator(String type){
        super.m_applet.m_strIndicator = type;
        draw_KLine.CreateIndicator();
        super.m_applet.repaint();
    }

    private void zoomOutKLineGraph() {
    }

    private void zoomInKLineGraph() {
    }

    void setCycleSubMenusAllTure() {
    }

    void setAllMenusEnable() {
    }

    private void executeQuoteCommand() {
        super.m_applet.UserCommand("60");
    }

    private void executeMarketCommand() {
        super.m_applet.UserCommand("80");
    }

}
