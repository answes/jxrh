// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   HQApplet.java

package chart;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Referenced classes of package gnnt.MEBS.HQApplet:
//            HQApplet

final class HQApplet3 extends KeyAdapter {

    final HQApplet this$0; /* synthetic field */

    HQApplet3(HQApplet hqapplet) {
        this$0 = hqapplet;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int iKey = e.getKeyCode();
        if(iKey != 33 && iKey != 34)
            this$0.this_keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int iKey = e.getKeyCode();
        if(iKey == 33 || iKey == 34)
            this$0.this_keyPressed(e);
    }
}
