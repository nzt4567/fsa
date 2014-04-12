package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.CnodeAccept;

/**
 * Handles loding rounded accept states
 * @author kvasnict
 */
public class CloadNAccept extends CloadNode {

    /**
     * Default constructor
     * @param r record in .fsm file
     */
    public CloadNAccept(String[] r) {
        super(r);
    }

    @Override
    public boolean execute(Cmodel m) {
        CnodeAccept n = new CnodeAccept(m_x, m_y);
        n.setLabel(new Clabel(m_lab, m_sup, m_sub));
        
        return m.addNode(n);
    }
}