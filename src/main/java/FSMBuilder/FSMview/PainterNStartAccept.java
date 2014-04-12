package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Cnode;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 */
class PainterNStartAccept extends PainterNStart {

    public PainterNStartAccept(Graphics g, Cnode n) {
        super(g, n);
        drawCircle(m_node.getCenter(), Imisc.NODE_RADIUS + 
                Imisc.ACCEPT_NODE_OFFSET);
    }   
}