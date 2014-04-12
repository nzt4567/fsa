package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.simulator.Csimulator;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 */
public class PainterInput {
    protected final Csimulator m_simulator;
    protected final Graphics m_graphics;
    
    public PainterInput(Graphics g, Csimulator s) {
        m_simulator = s;
        m_graphics = g;
    }
    
   public void drawAll() {
       String start = "INPUT: ";
       String read = m_simulator.getInput().substring(m_simulator.getInputIdx());
               
       m_graphics.drawString("INPUT: " + read, 10, 15);
   }
}
