// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   HQApplet.java

package chart;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import chart.domain.TradeDataDesc;
import chart.domain.TradeMaketEntity;
import chart.domain.TradeMarket;
import chart.util.HttpUtils;
import chart.util.ProductDataVO;
import chart.util.TradeTimeVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.List;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            ProductData, HttpThread, SendThread, ReceiveThread, 
//            RHColor, Page_Bottom, InputDialog, Page_KLine, 
//            Draw_KLine, Page_Main, Common, Page_MarketStatus, 
//            Page_MultiQuote, CodeTable, Page_MinLine, AboutDialog, 
//            Page_Bill, Page_History

public class HQApplet extends JPanel
    implements FocusListener {

    private static final long serialVersionUID = 0x5ba23793a3c0f861L;
    private boolean isStandalone;
    static final int PAGE_MULTIQUOTE = 0;
    static final int PAGE_MINLINE = 1;
    static final int PAGE_KLINE = 2;
    static final int PAGE_F10 = 3;
    static final int PAGE_BILL = 4;
    static final int PAGE_MARKETSTATUS = 5;
    static final int PAGE_HISTORY = 6;
    public int iCurrentPage;
    public String strCurrentCode;
    String marketId;
    String indexMainCode;
    int m_iKLineCycle;
    String m_strIndicator;
    int m_iCodeDate;
    int m_iCodeTime;
    int m_iDate;
    int m_iTime;
    //TradeTimeVO m_timeRange[];
    public TradeTimeVO m_timeRange[];
    int m_iMinLineInterval;
    Vector m_codeList;
    Hashtable m_htProduct;
    Vector vProductData;
    private Rectangle m_rcMain;
    private Rectangle m_rcBottom;
    public Page_Main mainGraph;
//    Page_Bottom bottomGraph;
    public static RHColor rhColor = null;
    String strSocketIP;
    int iSocketPort;
    String strURLPath;
    Socket socket;
//    SendThread sendThread;
//    ReceiveThread receiveThread;
//    HttpThread httpThread;
    boolean bRunning;
    PopupMenu popupMenu;
    static int bDebug = 0;
    int iShowBuySellPrice;
    String m_strMarketName;
    int m_bShowIndexAtBottom;
    int m_bShowIndexKLine;
    int m_iPrecision;
    int m_iPrecisionIndex;
    ResourceBundle m_resourceBundle;
    String strLanguageName;
    boolean bInputDlgShow;
    boolean bAboutDlgShow;
    private boolean m_bEndPaint;
    private Image m_img;

    private int index;

    public String getParameter(String key, String def) {
        return  System.getProperty(key, def);
    }

    public static void main(String args[]) {

    }
    public Page_Main getPageMain(){
        return mainGraph;
    }
    ProductData GetProductData(String code) {
        for(int i = 0; i < vProductData.size(); i++)
            if(((ProductData)vProductData.elementAt(i)).sCode.equals(code))
                return (ProductData)vProductData.elementAt(i);

        return null;
    }

    public HQApplet(int index) {
        this.index = index;
        isStandalone = true;
        iCurrentPage = 1;
        strCurrentCode = "";
        indexMainCode = "";
        m_iKLineCycle = 1;
        /**
         * 默认显示指标
         */
        m_strIndicator = "MACD";
        m_iDate = 0;
        m_iTime = 0;

        /**
         * 分时线间隔大小（秒）
         */
        m_iMinLineInterval = 60;
        m_codeList = new Vector();
        m_htProduct = new Hashtable();
        //产品信息
        vProductData = new Vector();
        m_rcMain = null;
        m_rcBottom = null;
        mainGraph = null;
//        bottomGraph = null;
        socket = null;
        bRunning = true;
        popupMenu = new PopupMenu();
        m_strMarketName = "";
        m_bShowIndexAtBottom = 1;
        m_bShowIndexKLine = 0;
        m_iPrecision = 0;
        m_iPrecisionIndex = 2;
        strLanguageName = "zh";
        bInputDlgShow = false;
        bAboutDlgShow = false;
        System.out.println("new HQApplet ");
        init();
    }
    
    

    private void init() {
        bRunning = true;
        try {
        	//初始化
            jbInit();
        }
        catch(Exception e) {
            if(bDebug != 0)
                e.printStackTrace();
        }
        if(bDebug != 0)
            System.out.println("init HQApplet ");
    }

    private void jbInit() throws Exception {
    	//数据源
        // 资源文件
        try {
            m_resourceBundle = ResourceBundle.getBundle("rc/string", new Locale(strLanguageName, ""));
        }
        catch(Exception e) {
            System.out.println("Language resource loaded failed !");
            e.printStackTrace();
        }
        m_rcMain = null;
        //请求市场列表
        ReceiveStockQuote();
        
        String strColorStyle = getParameter("ColorStyle", "0");
        rhColor = new RHColor(Integer.parseInt(strColorStyle));
        //设置背景颜色
        setBackground(rhColor.clBackGround);
        //添加事件
        addComponentListener(new HQApplet2(this));
        //添加按键监听事件
        addKeyListener(new HQApplet3(this));
        //添加鼠标事件
        addMouseListener(new HQApplet4(this));
        addMouseMotionListener(new HQApplet5(this));
        //添加焦点事件
        addFocusListener(this);
        this_componentResized(null);
        //底部视图
//        bottomGraph = new Page_Bottom(getGraphics(), m_rcBottom, this);
        requestFocus();
        showPageMinLine();
        // K线图标识
    }

    /**
     * 获取市场
     */
    private ProductDataVO[] GetData(){

        //獲取所有市场
        HttpUtils utils = new HttpUtils();
        String jsonStr = utils.get("/platform/trade/getMarketList.m");

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<TradeMaketEntity> markets = JSON.parseArray(JSON.toJSONString(jsonObject.get("result")),TradeMaketEntity.class);

        ProductDataVO[] test = new ProductDataVO[markets.size()];
        for(int i = 0;i < markets.size();i++){
            ProductDataVO data = new ProductDataVO();

            TradeMaketEntity tradeMaketEntity = markets.get(i);
            TradeDataDesc tradeNewest = tradeMaketEntity.getNewest();
            TradeMarket tradeMarket = tradeMaketEntity.getTradeMarket();

            if(i == index){
                marketId = tradeMarket.getId();
                strCurrentCode = tradeMarket.getName();
            }

            data.id = tradeMarket.getId();
            data.timeRange = tradeMarket.getTradeTime();

            data.zhang = tradeMarket.getZhang().floatValue();

            data.code = tradeMarket.getName();
            data.time = new Date();

            data.closePrice = tradeNewest.getClosePrice().floatValue();
            data.openPrice =  tradeNewest.getOpenPrice().floatValue();
            data.highPrice = tradeNewest.getMaxPrice().floatValue();
            data.lowPrice = tradeNewest.getMinPrice().floatValue();

            data.curPrice = tradeNewest.getClosePrice().floatValue();

            data.totalAmount = tradeNewest.getMinPrice().longValue();;
            data.totalMoney = tradeNewest.getMinPrice().floatValue();;
            data.curAmount = 0;
            data.amountRate = 0;
            data.balancePrice = tradeMarket.getOpenPrice().floatValue();
            data.yesterBalancePrice = tradeMarket.getClosePrice().floatValue();
            data.reserveChange = -1;

            test[i] = data;

        }

        return test;
    }

    TradeTimeVO[] initTimeRange(String timeRange){
        String[] time = timeRange.split(",");

        TradeTimeVO[] ret = new TradeTimeVO[time.length];

        for(int i=0;i<time.length;i++){
            TradeTimeVO timeVO = new TradeTimeVO();
            String[] hhmmss = time[i].split("-");

            for(int j = 0; j < hhmmss.length;j++){

                if(j == 0){
                    String start = hhmmss[j].replace(":","");
                    timeVO.beginTime = Integer.parseInt(start);
                }else{
                    String end = hhmmss[j].replace(":","");
                    timeVO.endTime = Integer.parseInt(end);
                }

            }

            ret[i] = timeVO;
        }

        return  ret;
    }

    /**
     *
     * 接收数据
     * 设置产品信息
     */
    private void ReceiveStockQuote() throws IOException {
        //解析后数据
        ProductDataVO data[] = GetData();

        String sCode = "";
        for(int i = 0; i < data.length; i++) {
            sCode = data[i].code;
            ProductData stock = this.GetProductData(sCode);
            if(stock == null) {
                if(this.vProductData.size() > 50)
                    this.vProductData.removeElementAt(50);
                stock = new ProductData();
                stock.m_timeRange = initTimeRange(data[i].timeRange);
                stock.id = data[i].id;
                stock.sCode = sCode;
                stock.realData = data[i];
                this.vProductData.insertElementAt(stock, 0);
            } else {
                stock.realData = data[i];
            }
        }
//        if(data.length > 0 && (2 == this.iCurrentPage || 1 == this.iCurrentPage) && this.strCurrentCode.equals(sCode))
//            //重新绘制整个画布
//            this.repaint();
//        if(data.length > 0 && this.m_bShowIndexAtBottom == 1 && this.indexMainCode.length() > 0 && data[0].code.equalsIgnoreCase(this.indexMainCode))
//            this.repaintBottom();


    }


    public String getAppletInfo() {
        return "Applet Information \u91D1\u7F51\u5B89\u6CF0";
    }

    public String[][] getParameterInfo() {
        return null;
    }

    public Frame getParentFrame(Component c) {
        Frame mainFrame = null;
        Container dad;
        for(dad = c.getParent(); !(dad instanceof Frame) && dad != null; dad = dad.getParent());
        if(dad instanceof Frame)
            mainFrame = (Frame)dad;
        return mainFrame;
    }

    void this_keyPressed(KeyEvent e) {
//        if(bInputDlgShow)
//            return;
//        char ch = e.getKeyChar();
//        if(Character.isLetterOrDigit(ch)) {
//            Frame f = getParentFrame(this);
//            Rectangle rc = getBounds();
//            Point p = getLocationOnScreen();
//            int x = (p.x + rc.width) - 125;
//            int y = (p.y + rc.height) - 160;
//            InputDialog dlg = new InputDialog(f, ch, this);
//            dlg.setBounds(x, y, 125, 160);
//            bInputDlgShow = true;
//            dlg.show();
//            bInputDlgShow = false;
//            String str = dlg.strCmd;
//            if(str == null || str.length() == 0)
//                return;
//            switch(str.charAt(0)) {
//            case 65: // 'A'
//                UserCommand(str.substring(1));
//                break;
//
//            case 84: // 'T'
//                m_strIndicator = str.substring(1);
//                ((Page_KLine)mainGraph).draw_KLine.CreateIndicator();
//                repaint();
//                break;
//
//            case 67: // 'C'
//                rhColor = new RHColor(str.charAt(1) - 48);
//                setBackground(rhColor.clBackGround);
//                break;
//
//            case 80: // 'P'
//                QueryStock(str.substring(1));
//                break;
//            }
//            repaint();
//            return;
//        }
        boolean bNeedRepaint = true;
        int iKey = e.getKeyCode();
        switch(iKey) {
//        case 27: // '\033'
//            if(mainGraph != null) {
//                bNeedRepaint = mainGraph.KeyPressed(e);
//                if(!bNeedRepaint && iCurrentPage != 0) {
//                    byte type = (byte)Common.GetProductType(strCurrentCode);
//                    if(iCurrentPage == 5)
//                        type = ((Page_MarketStatus)mainGraph).currentStockType;
//                    mainGraph = new Page_MultiQuote(m_rcMain, this, type);
//                    bNeedRepaint = true;
//                }
//            }
//            break;
//
//        case 112: // 'p'
//            if(iCurrentPage == 1 || iCurrentPage == 2)
//                UserCommand("01");
//            break;
//
//        case 113: // 'q'
//            UserCommand("60");
//            break;
//
//        case 114: // 'r'
//            if(indexMainCode.length() > 0)
//                UserCommand("INDEX_" + indexMainCode);
//            break;
//
//        case 115: // 's'
//            UserCommand("80");
//            break;

        case 116: // 't'
            OnF5();
            break;
//
//        case 118: // 'v'
//            UserCommand("70");
//            break;
        // Page_MinLiine，Page_KLine响应点击事件
        default:
            if(mainGraph != null)
//            	System.out.println(this.getClass().getName()+"---->"+mainGraph.toString());
                bNeedRepaint = mainGraph.KeyPressed(e);
            break;
        }
//        if(bNeedRepaint)
            repaint();
    }

    /**
     * 改变产品（市场）
     * @param bUp
     * @param bIgnoreStatus
     */
    void ChangeStock(boolean bUp, boolean bIgnoreStatus) {
//        System.out.println("<----ChangeStock----->");
        int iIndex = -1;
        for(int i = 0; i < m_codeList.size(); i++) {
            if(!strCurrentCode.equals(m_codeList.elementAt(i)))
                continue;
            iIndex = i;
            break;
        }

        if(iIndex == -1) {
            if(m_codeList.size() > 0)
                strCurrentCode = (String)m_codeList.elementAt(0);
        } else {
            if(bUp) {
                if(--iIndex < 0)
                    iIndex = m_codeList.size() - 1;
            } else
            if(++iIndex >= m_codeList.size())
                iIndex = 0;
            strCurrentCode = (String)m_codeList.elementAt(iIndex);
        }
        if(!bIgnoreStatus) {
            CodeTable s = (CodeTable)m_htProduct.get(strCurrentCode);
            if(s.status == 1 || s.status == 4) {
                ChangeStock(bUp, bIgnoreStatus);
                return;
            }
        }
        if(1 == iCurrentPage)
            mainGraph = new Page_MinLine(m_rcMain, this);
        else
        if(2 == iCurrentPage)
            mainGraph = new Page_KLine(m_rcMain, this);
    }

    /**
     * 鼠标左键
     * @param e
     */
    void this_mouseLeftPressed(MouseEvent e) {

        if(mainGraph == null) {
            return;
        }
        mainGraph.MouseLeftClicked(e.getX(), e.getY());
        repaint();
    }

    void this_mouseRightReleased(MouseEvent e) {
        if(mainGraph == null) {
            return;
        } else {
            mainGraph.processMenuEvent(popupMenu, e.getX(), e.getY());
            return;
        }
    }

    /**
     * 鼠标点击
     * @param e
     */
    void this_mouseLeftDblClicked(MouseEvent e) {
        if(mainGraph == null)
            return;
//        if(mainGraph.MouseLeftDblClicked(e.getX(), e.getY()))
//            repaint();
    }

    /**
     * 鼠标移动
     * @param e
     */
    void this_mouseMoved(MouseEvent e) {
        if(mainGraph == null)
            return;
//        mainGraph.MouseMoved(e.getX(), e.getY());
        mainGraph.MouseDragged(e.getX(), e.getY());
        repaint();
    }

    /**
     * 鼠標拖動
     * @param e
     */
    void this_mouseDragged(MouseEvent e) {
        if(mainGraph == null)
            return;
//        mainGraph.MouseDragged(e.getX(), e.getY());
//        repaint();
    }

    void this_componentResized(ComponentEvent e) {
        Dimension d = getSize();
        m_rcMain = new Rectangle(d);

        if(mainGraph != null)
            mainGraph.m_rc = m_rcMain;
//        m_rcBottom = new Rectangle(d);
//        m_rcMain.height -= 20;
//
//        if(mainGraph != null)  m_rcBottom.y = m_rcMain.y + m_rcMain.height;
//        m_rcBottom.height = 20;
//            mainGraph.m_rc = m_rcMain;
//        if(bottomGraph != null)
//            bottomGraph.rc = m_rcBottom;

    }

    /**
     * 切换分时图，K线图
     * @param sCode 市场的code
     */
    void QueryStock(String sCode) {
        strCurrentCode = sCode;
        if(2 == iCurrentPage)
            mainGraph = new Page_KLine(m_rcMain, this);
        else
            mainGraph = new Page_MinLine(m_rcMain, this);
    }

    /**
     * 
     * @param sCmd 显示页面类型
     */
    void UserCommand(String sCmd) {
//        if(sCmd.equals("about") && !bAboutDlgShow) {
//            Frame f = getParentFrame(this);
//            Rectangle rc = getBounds();
//            Point p = getLocationOnScreen();
//            int x = (p.x + rc.width / 2) - 110;
//            int y = (p.y + rc.height / 2) - 60;
//            AboutDialog dlg = new AboutDialog(f, this);
//            dlg.setBounds(x, y, 220, 120);
//            bAboutDlgShow = true;
//            dlg.show();
//            bAboutDlgShow = false;
//            return;
//        }
//        if(sCmd.startsWith("INDEX_")) {
//            strCurrentCode = sCmd.substring(6);
//            mainGraph = new Page_KLine(m_rcMain, this);
//            return;
//        }
//        if(sCmd.startsWith("SERIES_")) {
//            strCurrentCode = sCmd.substring(7);
//            mainGraph = new Page_KLine(m_rcMain, this);
//            return;
//        }
//        if(sCmd.equals("page_history")) {
//            UserCommand("70");
//            return;
//        }
//        int iCmd = Integer.parseInt(sCmd);
//        if(bDebug != 0)
//            System.out.println("sCmd = ==" + iCmd);
//        switch(iCmd) {
//        case 1: // '\001'
//            mainGraph = new Page_Bill(m_rcMain, this);
//            break;
//
//        case 5: // '\005'
//            OnF5();
//            break;
//
//        case 60: // '<'
//            mainGraph = new Page_MultiQuote(m_rcMain, this, (byte)1);
//            try {
//                receiveThread.ReceiveClassSort(null);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            break;
//
//        case 80: // 'P'
//            mainGraph = new Page_MarketStatus(m_rcMain, this, (byte)1);
//            break;
//
//        case 70: // 'F'
//            mainGraph = new Page_History(m_rcMain, this);
//            break;
//
//        }
    }

    void ShowMutilQuote(byte stockType) {
//        mainGraph = new Page_MultiQuote(m_rcMain, this, stockType);
//        repaint();
    }

    /**
     * 切换分时图/K线图
     */
    void OnF5() {
        if(strCurrentCode.length() == 0)
            return;
        if(1 == iCurrentPage) {
            mainGraph = new Page_KLine(m_rcMain, this);
            iCurrentPage = 2;
        } else {
            mainGraph = new Page_MinLine(m_rcMain, this);
            iCurrentPage = 1;
        }
    }

    /**
     * 切换市场用
     * @param stockCode 市场标识
     */
    void showPage(String stockCode){
        if(iCurrentPage == 1){
            showPageMinLine(stockCode);
        }else{
            showPageKLine(stockCode);
        }
    }

    /**
     * 显示指定市场的分时图
     * @param stockCode
     */
    void showPageMinLine(String stockCode) {
        strCurrentCode = stockCode;
        mainGraph = new Page_MinLine(m_rcMain, this);
        iCurrentPage = 1;
        repaint();
    }

    /**
     * 显示指定市场的K线图
     * @param stockCode
     */
    void showPageKLine(String stockCode) {
        strCurrentCode = stockCode;
        mainGraph = new Page_KLine(m_rcMain, this);
        iCurrentPage = 2;
        repaint();
    }

    /**
     * 显示分时线
     */
   public void showPageMinLine() {
        mainGraph = new Page_MinLine(m_rcMain, this);
        iCurrentPage = 1;
        repaint();
    }

    /**
     * 显示K线
     */
    public void showPageKLine() {
        mainGraph = new Page_KLine(m_rcMain, this);
        iCurrentPage = 2;
        repaint();
    }
    
    /**
     * 重绘底部区域
     */
    void repaintBottom() {
//        if(bottomGraph != null)
//            bottomGraph.Paint();
    }

    void Repaint(Rectangle rc) {
        repaint(rc.x, rc.y, rc.width + 1, rc.height + 1);
    }

    public void focusLost(FocusEvent focusevent) {
    }

    public void focusGained(FocusEvent event) {
        repaint();
    }

    public void update(Graphics g) {
        paint(g);
    }
    //init--start--paint--stop--destroy
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(m_rcMain == null)
            return;
        m_bEndPaint = false;
        try {
            m_img = createImage(m_rcMain.width, m_rcMain.height);
        }
        catch(Exception exception) { }
        if(mainGraph != null) {
            Graphics myG = m_img.getGraphics();
            if(myG != null) {
                myG.clearRect(0, 0, m_rcMain.width, m_rcMain.height);
                mainGraph.Paint(myG);
            }
        }
        EndPaint();
//        if(bottomGraph != null)
//            bottomGraph.Paint();
    }

    public void EndPaint() {
//        System.out.println("结束绘图");
        if(!m_bEndPaint) {
            getGraphics().setPaintMode();
            getGraphics().drawImage(m_img, m_rcMain.x, m_rcMain.y, this);
        }
        m_bEndPaint = true;
    }

    public void destroy() {
        mainGraph.stopFlag = true;
        mainGraph = null;
        bRunning = false;
//        httpThread.AskForData(null);
//        sendThread.AskForData(null);
        try {
            if(socket != null)
                socket.close();
        }
        catch(IOException e) {
            System.out.println("eroo111");
            e.printStackTrace();
        }
        socket = null;
//        httpThread = null;
//        receiveThread = null;
        if(bDebug != 0)
            System.out.println("destroy HQApplet ");
    }

    int GetPrecision(String sCode) {
        int iType = getProductType(sCode);
        switch(iType) {
        case 2: // '\002'
        case 3: // '\003'
            return m_iPrecisionIndex;
        }
        return m_iPrecision;
    }

    int getProductType(String code) {
        CodeTable codeTable = (CodeTable)m_htProduct.get(code);
        if(codeTable == null)
            return -1;
        else
            return codeTable.status;
    }

    boolean isIndex(String code) {
        int iType = getProductType(code);
        return iType == 2 || iType == 3;
    }

    String getShowString(String key) {
        String strShow = "";
        try {
            String s = m_resourceBundle.getString(key);
            strShow = new String(s.getBytes("8859_1"), "GBK");
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return strShow;
    }

}
