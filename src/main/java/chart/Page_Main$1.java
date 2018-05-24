// NMI's Java Code Viewer 6.0a
// www.trinnion.com/javacodeviewer

// Registered to Evaluation Copy                                      
// Generated PGFZKD AyTB 14 2007 15:44:19 

//source File Name:   Page_Main.java

package chart;


// Referenced classes of package gnnt.MEBS.HQApplet:
//            Page_Main, HQApplet

final class Page_Main$1 extends Thread {

    final Page_Main this$0; /* synthetic field */

    Page_Main$1(Page_Main page_main) {
        this$0 = page_main;
    }

    public void run() {
        try {
            while(!this$0.stopFlag)  {
                Thread.sleep(5000L);
                if(!this$0.stopFlag)
                    this$0.AskForDataOnTimer();
            }
        }
        catch(InterruptedException e) {
            if(HQApplet.bDebug != 0)
                e.printStackTrace();
        }
    }
}
