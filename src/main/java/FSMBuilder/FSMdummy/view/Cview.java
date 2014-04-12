package FSMBuilder.FSMdummy.view;

import FSMBuilder.FSMdummy.model.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author kvasnict
 * This is just a dummy testing class which should be completely rewritten.
 * Design of the view should be totally different. Controller needs to call a 
 * repaint (see swing documentation) method which should call all appropriate
 * paintComponent methods. As long as this is followed it will work.
 */

public class Cview extends JPanel {
    private static final int m_WIDTH = 900;
    private static final int m_HEIGHT = 200;
    private final Cmodel m_fsm;
    
    /**
     * Default constructor
     * @param model model
     */
    public Cview(Cmodel model) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        m_fsm = model;
    }
    
    // TODO: avoid getter usage
    private void drawNode(Graphics g, Cnode n) {
        g.drawOval(n.getX(), n.getY(), 10, 10);
    }
    
    // TODO: avoid getter usage
    // TODO: transition from node to itself is not drawn -> 
    // TODO: need to solve this by curved_line maybe? dnt know yet
    private void drawTransition(Graphics g, Ctransition t) {
        g.drawLine(t.getFrom().getX(), t.getFrom().getY(), 
                t.getTo().getX(), t.getTo().getY());
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(m_WIDTH, m_HEIGHT);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        for (Cnode cnode : m_fsm.getNodes()) {
            drawNode(g, cnode);
        }
        
        for (Ctransition ctransition : m_fsm.getTransitions()) {
            drawTransition(g, ctransition);
        }
    }
}