package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.CnodeStartAccept;

/**
 *
 * @author kvasnict
 */
class CloadNStartAccept extends CloadNode {

    public CloadNStartAccept(String[] rec) {
        super(rec);
    }

    @Override
    public boolean execute(Cmodel m) {
        CnodeStartAccept n = new CnodeStartAccept(m_x, m_y);
        n.setLabel(new Clabel(m_lab, m_sup, m_sub));
        
        return m.addNode(n);
    }
    
}
