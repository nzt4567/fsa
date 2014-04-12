package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.fileIO.dotFSM.CloadTrans;
import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMmodel.CtransNormal;

/**
 * Handles loading straight transitions
 * @author kvasnict
 */
public class CloadTransNormal extends CloadTrans {
    
    /**
     * Default constructor
     * @param r record in .fsm file
     * @param m model
     */
    public CloadTransNormal(String[] r, Cmodel m) {
        super(r, m);
    }

    @Override
    public Ctrans execute() {
        Cnode from = m_fsm.getNode(m_fx, m_fy);
        Cnode to = m_fsm.getNode(m_tx, m_ty);
        
        if (from == null || to == null)
            throw new RuntimeException("nodes for transitions dnt exist");
        
        CtransNormal t = new CtransNormal(from, to);
        t.setLabel(new Clabel(m_lab, m_sup, m_sub));
        
        return t;
    }   
}