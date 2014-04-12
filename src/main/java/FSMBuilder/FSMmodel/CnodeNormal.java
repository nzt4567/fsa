package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;

/**
 * Normal states
 * @author Logan Fine
 */
public class CnodeNormal extends Cnode {

    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public CnodeNormal(Integer x, Integer y) {
        super(x, y);
    }
    
    @Override
    public String toString() {
        return super.toString() + Ifile.SUB_N_NORMAL + "\n"; 
    }

    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }
}