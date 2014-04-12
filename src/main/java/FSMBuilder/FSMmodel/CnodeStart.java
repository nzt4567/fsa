package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;

/**
 * Start states
 * @author Logan Fine
 */
public class CnodeStart extends Cnode {
    
    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public CnodeStart(Integer x, Integer y) {
        super(x, y);
    }
    
    @Override
    public String toString() {
        return super.toString() + Ifile.SUB_N_START + "\n"; 
    }

    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }
}