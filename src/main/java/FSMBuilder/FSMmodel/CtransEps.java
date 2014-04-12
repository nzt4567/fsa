package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;

/**
 * Holds epsilon transitions
 * @author kvasnict
 */
public class CtransEps extends Ctrans {
    
    /**
     * Default constructor
     * @param from source node
     * @param to finish node
     */
    public CtransEps(Cnode from, Cnode to) {
        super(from, to);
    }
    
    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }   

    @Override
    public String toString() {
        return super.toString() + Ifile.SUB_T_EPS + "\n";
    }
}
