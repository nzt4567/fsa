package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Cpoint;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 * Abstract class that is used to draw every rounded state
 */
public abstract class NodePainter extends Painter {
    protected final Cnode m_node;
    
    /**
     * Default constructor
     * @param g 2d graphics
     * @param n node
     */
    public NodePainter(Graphics g, Cnode n) {
        super(g);
        
        if (n != null)
            m_node = n;
        else
            throw new RuntimeException("null in drawer node");
        
        drawLabel(m_node.getLabel());
        drawCircle(m_node.getCenter(), Imisc.NODE_RADIUS);
    }
        
    @Override
    public final void drawLabel(Clabel l) {
        drawLabelWithEverythig(l, m_node.getCenter(), Imisc.NODE_RADIUS);
    }
    
    /**
     * Draw a single circle for every rounded node
     * @param c center of the node
     * @param r radius of the node
     */
    public final void drawCircle(Cpoint c, Integer r) {
        m_graphics.drawOval(c.m_x - r, c.m_y - r, 2 * r, 2 * r);
    }
}