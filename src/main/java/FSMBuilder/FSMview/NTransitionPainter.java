package FSMBuilder.FSMview;

import FSMBuilder.FSMmodel.CtransNormal;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Duri Abdurahman Duri
 * Allowes for the drawing of stright transitions
 */
public class NTransitionPainter extends TransitionPainter {

    /**
     * Default constructor
     * @param g 2d graphics
     * @param t transition
     */
    public NTransitionPainter(Graphics g, CtransNormal t) {
        super(g, t);
        int f_x, f_y, t_x, t_y;
        int LINELEN = 5;
        int ARR_SIZE = 8;
        
        if (t.getFrom().equals(t.getTo())) {
            f_x = t.getFrom().getCenter().m_x - LINELEN;
            t_x = t.getTo().getCenter().m_x + LINELEN;
            
            if (f_x < 0)
                f_x = 0;
        } else {
            f_x = t.getFrom().getCenter().m_x;
            t_x = t.getTo().getCenter().m_x;
        }
        
        f_y = t.getFrom().getCenter().m_y;
        t_y = t.getTo().getCenter().m_y;
        
        double dx = t_x - f_x, dy = t_y - f_y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(f_x, f_y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        m_graphics.transform(at);
        
        m_graphics.drawLine(0, 0, len, 0);
        m_graphics.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}