// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   HQApplet.java

package chart;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.awt.event.WindowEvent;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            HQApplet

public class HQApplet1 extends JFXPanel {

    private static final long serialVersionUID = 0x5ee6fe2cdea8105dL;
    public  HQApplet val$applet; /* synthetic field */

    public HQApplet1(HQApplet hqapplet) throws HeadlessException {
        val$applet = hqapplet;
    }

    protected void processWindowEvent(WindowEvent e) {
//        super.processWindowEvent(e);
        if(e.getID() == 201) {
            val$applet.destroy();
            System.exit(0);
        }
    }

    public synchronized void setTitle(String title) {
//        super.setTitle("≤‚ ‘");
        enableEvents(64L);
    }
}
