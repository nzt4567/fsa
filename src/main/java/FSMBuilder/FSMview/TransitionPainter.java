package FSMBuilder.FSMview;

import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cpoint;
import FSMBuilder.FSMmodel.Ctrans;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 * Thus method extracts the drawing of transitions 
 */
public abstract class TransitionPainter extends Painter {
    protected final Ctrans m_transition;

    /**
     * Default constructor
     * @param g 2d graphics
     * @param t transition
     */
    public TransitionPainter(Graphics g, Ctrans t) {
        super(g);
        if (t == null)
            throw new RuntimeException("null in trans draw");
        
        m_transition = t;
        drawLabel(t.getLabel());
    }
    
    @Override
    public final void drawLabel(Clabel l) {
        Integer f_x = m_transition.getFrom().getCenter().m_x;
        Integer f_y = m_transition.getFrom().getCenter().m_y;
        Integer t_x = m_transition.getTo().getCenter().m_x;
        Integer t_y = m_transition.getTo().getCenter().m_y;
        Integer m_x = (f_x + t_x) / 2;
        Integer m_y = (f_y + t_y) / 2;
        
        drawLabelWithEverythig(l, new Cpoint(m_x, m_y), 0);
    }
}