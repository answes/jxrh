// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Page_Main.java

package chart;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            MenuListener, HQApplet, CodeTable

abstract class Page_Main extends MenuListener {

    boolean stopFlag;
    protected Rectangle m_rc;
    Thread timerThread;

    public Page_Main(Rectangle _rc, HQApplet applet) {
        stopFlag = false;
        timerThread = new Page_Main$1(this);
        super.m_applet = applet;
        m_rc = _rc;
        if(super.m_applet.mainGraph != null)
            super.m_applet.mainGraph.stopFlag = true;
        timerThread.start();
    }

    abstract void Paint(Graphics g);

    abstract boolean KeyPressed(KeyEvent keyevent);

    abstract void changeCycle(String cycle);

    abstract void changeIndicator(String type);

    void AskForDataOnTimer() {
    }

    boolean MouseLeftClicked(int x, int y) {
        return false;
    }

    boolean MouseLeftDblClicked(int x, int y) {
        return false;
    }

    boolean MouseDragged(int x, int y) {
        return false;
    }

    boolean MouseMoved(int x, int y) {
        return false;
    }

    abstract void processMenuEvent(PopupMenu popupmenu, int i, int j);

    protected void processCommonMenuEvent(PopupMenu popupMenu, ActionListener listener) {
        MenuItem menuHistory = new MenuItem(super.m_applet.getShowString("History") + "  F7");
        menuHistory.setActionCommand("page_history");
        menuHistory.addActionListener(listener);
        popupMenu.add(menuHistory);
        MenuItem menuAbout = new MenuItem(super.m_applet.getShowString("About") + " ...");
        menuAbout.setActionCommand("about");
        menuAbout.addActionListener(listener);
        popupMenu.addSeparator();
        popupMenu.add(menuAbout);
        int iCount = 0;
        Menu menuSub = new Menu(super.m_applet.getShowString("ClassIndex"));
        for(int i = 0; i < super.m_applet.m_codeList.size(); i++) {
            String code = (String)super.m_applet.m_codeList.elementAt(i);
            CodeTable codeTable = (CodeTable)super.m_applet.m_htProduct.get(code);
            if(codeTable.status == 2 || codeTable.status == 3) {
                MenuItem menuIndex;
                if(codeTable.status == 3)
                    menuIndex = new MenuItem(codeTable.sName + "  F3");
                else
                    menuIndex = new MenuItem(codeTable.sName);
                menuIndex.setActionCommand("INDEX_" + code);
                menuIndex.addActionListener(listener);
                if(codeTable.status == 3)
                    popupMenu.insert(menuIndex, 0);
                else
                    menuSub.add(menuIndex);
                iCount++;
            }
        }

        if(iCount > 0)
            popupMenu.insertSeparator(1);
        if(menuSub.getItemCount() > 0)
            popupMenu.insert(menuSub, 0);
        Menu MenuSeriesSub = new Menu(super.m_applet.getShowString("SeriesPrice"));
        for(int i = 0; i < super.m_applet.m_codeList.size(); i++) {
            String code = (String)super.m_applet.m_codeList.elementAt(i);
            CodeTable codeTable = (CodeTable)super.m_applet.m_htProduct.get(code);
            if(codeTable.status == 4) {
                MenuItem menuSeries = new MenuItem(codeTable.sName);
                menuSeries.setActionCommand("SERIES_" + code);
                menuSeries.addActionListener(listener);
                MenuSeriesSub.add(menuSeries);
            }
        }

        if(MenuSeriesSub.getItemCount() > 0) {
            popupMenu.insert(MenuSeriesSub, 0);
            popupMenu.insertSeparator(1);
        }
    }
}
