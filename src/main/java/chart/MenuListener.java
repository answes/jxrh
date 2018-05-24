// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Page_Main.java

package chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            HQApplet

class MenuListener
    implements ActionListener {

    HQApplet m_applet;

    MenuListener() {
    }

    public void actionPerformed(ActionEvent e) {
        m_applet.UserCommand(e.getActionCommand());
        m_applet.repaint();
    }
}
