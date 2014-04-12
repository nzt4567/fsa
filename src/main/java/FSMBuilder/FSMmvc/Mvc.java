package FSMBuilder.FSMmvc;

import FSMBuilder.FSMview.View;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMcontroller.Ccontrol;
import javax.swing.JFrame;

/**
 * This just runs the application, no rocket science here. Will probably stay
 * this way for the rest of the time.
 * @author kvasnict
 */

public class Mvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mvc app = new Mvc();
        app.run();
    }
    
    /**
     *  Run the application
     */
    public void run() {
        JFrame f = new JFrame("AWESOME FSM DRAWING APPLICATION");
        Cmodel m = new Cmodel();
        View v = new View(m, f);
        Ccontrol c = new Ccontrol(v, m);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(v);
        f.pack();
        f.setVisible(true);
    }
}