package FSMBuilder.FSMdummy.model;

/**
 * Accept state node
 * @author kvasnict
 */
public class CnodeFinish extends Cnode {

    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public CnodeFinish(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "[F] (" + m_x + ", " + m_y + ") ";
    }
    
}

