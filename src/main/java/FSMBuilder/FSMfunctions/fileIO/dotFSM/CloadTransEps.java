package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.fileIO.dotFSM.CloadTrans;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Ctrans;

/**
 *
 * @author kvasnict
 */
class CloadTransEps extends CloadTrans {

    public CloadTransEps(String[] r, Cmodel m) {
        super(r, m);
    }

    @Override
    public Ctrans execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
