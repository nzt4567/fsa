package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.CnodeNormal;

/**
 * Handles loading rounded normal states
 * @author kvasnict
 */
public class CloadNNormal extends CloadNode {
    
    /**
     * Default constructor
     * @param r record in .fsm file
     */
    public CloadNNormal(String[] r) {
        super(r);
    }

    @Override
    public boolean execute(Cmodel m) {
        CnodeNormal n = new CnodeNormal(m_x, m_y);
        n.setLabel(new Clabel(m_lab, m_sup, m_sub));
        
        return m.addNode(n);
    }
}
