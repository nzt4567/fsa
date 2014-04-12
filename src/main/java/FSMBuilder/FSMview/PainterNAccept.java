package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Cnode;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 * draws accept states
 */
public class PainterNAccept extends NodePainter {

    /**
     * Default constructor
     * @param g 2d graphics
     * @param n node
     */
    public PainterNAccept(Graphics g, Cnode n) {
        super(g, n);        
        drawCircle(m_node.getCenter(), Imisc.NODE_RADIUS + 
                Imisc.ACCEPT_NODE_OFFSET);
    }
}
