// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   HQApplet.java

package chart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            HQApplet

final class HQApplet4 extends MouseAdapter {

    final HQApplet this$0; /* synthetic field */

    HQApplet4(HQApplet hqapplet) {
        this$0 = hqapplet;
    }

    public void mousePressed(MouseEvent e) {
        if(e.getModifiers() != 4 && e.getClickCount() == 1) {
            this$0.this_mouseLeftPressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getModifiers() == 4)
            this$0.this_mouseRightReleased(e);
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getModifiers() != 4 && e.getClickCount() > 1)
            this$0.this_mouseLeftDblClicked(e);
    }
}
