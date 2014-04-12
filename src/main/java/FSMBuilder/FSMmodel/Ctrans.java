package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMfunctions.constants.Imisc;
import java.awt.geom.Line2D;

/**
 * Base class for all types of transitions
 * @author Logan Fine
 */

public abstract class Ctrans extends CbasicFSMObject {
    protected final Cnode m_from;
    protected final Cnode m_to;
    
    /**
     * Default constructor
     * @param from source node
     * @param to finish node
     */
    public Ctrans(Cnode from, Cnode to) {
        super();
        if (from != null && to != null) {
            m_from = from;
            m_to = to;
        } else
            throw new RuntimeException("Ctransition creation on null nodes");
    }
    
    /**
     * from-node getter
     * @return Cnode
     */
    public Cnode getFrom() {
        return m_from;
    }
    
    /**
     * to-node getter
     * @return Cnode
     */
    public Cnode getTo() {
        return m_to;
    }
    
    @Override
    public boolean setLabel(Clabel l) {
        if (l != null) {
            m_label = l;
            m_label.parse_transition();
        }
        
        return l != null;
    }
    
    @Override
    public boolean hasCoords(Integer x, Integer y) {
        if (x != null && y != null) {
            Line2D l = new Line2D.Double(m_from.m_center.m_x, m_from.m_center.m_y,
                    m_to.m_center.m_x, m_to.m_center.m_y);
            int toleration = 10;
            int width = 20;
            int height = 20;

            return l.intersects(x - toleration, y - toleration, width, height); 
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return Ifile.TYPE_TRANS + Ifile.DELIMITER + m_from.m_center.toString() +
                m_to.m_center.toString() + super.toString();
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || ! this.getClass().equals(other.getClass()))
            return false;
        if (other == this)
            return true;
        
        Ctrans t = (Ctrans) other;
                
        return m_from.equals(t.m_from) && m_to.equals(t.m_to) && 
                m_label.equals(t.m_label);
    }

    @Override
    public int hashCode() {
        return Imisc.TRANS_HASH;
    }
}