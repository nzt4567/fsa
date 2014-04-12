package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;

/**
 * Concrete transition class
 * @author Logan Fine
 * @version 2
 */
public class CtransNormal extends Ctrans {

    /**
     * Default constructor
     * @param from source node
     * @param to finish node
     */
    public CtransNormal(Cnode from, Cnode to) {
        super(from, to);
    }
    
    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }   

    @Override
    public String toString() {
        return super.toString() + Ifile.SUB_T_NORMAL + "\n";
    }    
}