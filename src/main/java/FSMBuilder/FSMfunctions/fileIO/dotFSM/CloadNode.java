package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMmodel.Cmodel;

/**
 * Functor pattern
 * @author kvasnict
 */
public abstract class CloadNode {
    protected final Integer m_x;
    protected final Integer m_y;
    protected final String m_lab;
    protected final String m_sup;
    protected final String m_sub;
    
    /**
     * Default constructor
     * @param r record in .fsm file
     */
    public CloadNode(String[] r) {
        if (r != null) {
            m_x = new Integer(r[Ifile.IDX_N_X]);
            m_y = new Integer(r[Ifile.IDX_N_Y]);
            m_lab = r[Ifile.IDX_N_LABEL];
            m_sup = r[Ifile.IDX_N_SUPERSCR];
            m_sub = r[Ifile.IDX_N_SUBSCR];
        } else
            throw new RuntimeException("record is null");
    }
    
    /**
     * Function pointer - load the node
     * @param m model
     * @return true if success
     */
    public abstract boolean execute(Cmodel m);
}