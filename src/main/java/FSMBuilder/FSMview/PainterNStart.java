package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Cnode;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 * draws start states
 */
public class PainterNStart extends NodePainter {

    /**
     * Default constructor
     * @param g 2d graphics
     * @param n node
     */
    public PainterNStart(Graphics g, Cnode n) {
        super(g, n);
        drawStartNodeArrow(n);
    }
    
    /**
     * Draw a small arrown pointing to the start node
     * @param n node
     */
    public final void drawStartNodeArrow(Cnode n) {
        Integer t_x = n.getCenter().m_x;
        Integer t_y = n.getCenter().m_y;
        
        m_graphics.drawLine(t_x - Imisc.NODE_RADIUS - Imisc.START_NODE_LINE_LEN,
                t_y, t_x - Imisc.NODE_RADIUS, t_y);
        m_graphics.fillPolygon(new int[] {t_x - 20, t_x - 24, t_x - 24},
                              new int[] {t_y, t_y - 4, t_y + 4}, 3);
    } //TODO: use constants file Imisc
}
