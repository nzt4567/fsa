package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;

/**
 * Accepting states
 * @author Logan Fine
 */
public class CnodeAccept extends Cnode {

    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public CnodeAccept(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return super.toString() + Ifile.SUB_N_ACCEPT + "\n";
    }

    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }
}