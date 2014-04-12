package FSMBuilder.FSMdummy.model;

/**
 *
 * @author kvasnict
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
    
    public String toString() {
        return "[S] (" + m_x + ", " + m_y + ")";
    }
    
}