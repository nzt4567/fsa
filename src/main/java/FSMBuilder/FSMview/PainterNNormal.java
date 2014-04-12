package FSMBuilder.FSMview;

import FSMBuilder.FSMmodel.Cnode;
import java.awt.Graphics;

/**
 *
 * @author Duri Abdurahman Duri
 * draws normal nodes
 */
public class PainterNNormal extends NodePainter {

    /**
     * Default constructor
     * @param g 2d graphics
     * @param n node
     */
    public PainterNNormal(Graphics g, Cnode n) {
        super(g, n);
    }
}
