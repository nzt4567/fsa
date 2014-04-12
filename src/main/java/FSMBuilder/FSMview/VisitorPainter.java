package FSMBuilder.FSMview;

import FSMBuilder.FSMmodel.Ivisitor;
import FSMBuilder.FSMmodel.CnodeAccept;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.CnodeStart;
import FSMBuilder.FSMmodel.CnodeStartAccept;
import FSMBuilder.FSMmodel.CtransEps;
import FSMBuilder.FSMmodel.CtransNormal;
import java.awt.Graphics;

/**
 * Drawing visitor implementation - see wiki for visitor pattern
 * @author kvasnict
 */
public class VisitorPainter implements Ivisitor {
    private final Graphics m_graphics;

    /**
     * Default constructor
     * @param g 2d graphics
     */
    public VisitorPainter(Graphics g) {
        m_graphics = g;
    }
    
    @Override
    public void visit(CnodeAccept s) {
        new PainterNAccept(m_graphics, s);
    }

    @Override
    public void visit(CnodeNormal s) {
        new PainterNNormal(m_graphics, s);
    }

    @Override
    public void visit(CnodeStart s) {
        new PainterNStart(m_graphics, s);
    }

    @Override
    public void visit(CnodeStartAccept s) {
        new PainterNStartAccept(m_graphics, s);
    }
    
    @Override
    public void visit(CtransNormal t) {
        new NTransitionPainter(m_graphics, t);
    }
    
    @Override
    public void visit(CtransEps t) {
        new CdrawerTransEps(m_graphics, t);
    }
}
