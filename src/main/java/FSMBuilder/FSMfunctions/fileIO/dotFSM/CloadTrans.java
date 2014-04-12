package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Ctrans;

/**
 * Functor pattern
 * @author kvasnict
 */
public abstract class CloadTrans {
    protected final Integer m_fx;
    protected final Integer m_fy;
    protected final Integer m_tx;
    protected final Integer m_ty;
    protected final String m_lab;
    protected final String m_sup;
    protected final String m_sub;
    protected final Cmodel m_fsm;
    
    public CloadTrans(String[] r, Cmodel m) {
        if (r != null && m != null) {
            m_fx = new Integer(r[Ifile.IDX_T_FX]);
            m_fy = new Integer(r[Ifile.IDX_T_FY]);
            m_tx = new Integer(r[Ifile.IDX_T_TX]);
            m_ty = new Integer(r[Ifile.IDX_T_TY]);
            m_lab = r[Ifile.IDX_T_LABEL];
            m_sup = r[Ifile.IDX_T_SUPERSCR];
            m_sub = r[Ifile.IDX_T_SUBSCR];
            m_fsm = m;
        } else
            throw new RuntimeException("record/model is null");
    }
        
    /**
     * Functor pattern - function pointer
     * @return Ctrans
     */
    public abstract Ctrans execute();
}
