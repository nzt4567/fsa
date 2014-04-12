package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.CnodeStart;

/**
 * Handles loading rounded start states
 * @author kvasnict
 */
public class CloadNStart extends CloadNode {

    /**
     * Default constructor
     * @param r
     */
    public CloadNStart(String[] r) {
        super(r);
    }

    @Override
    public boolean execute(Cmodel m) {
        CnodeStart n = new CnodeStart(m_x, m_y);
        n.setLabel(new Clabel(m_lab, m_sup, m_sub));
        
        return m.addNode(n);
    }   
}