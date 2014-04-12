package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;
import java.awt.geom.Ellipse2D;
import FSMBuilder.FSMfunctions.constants.Imisc;

/**
 * Base class for all states
 * @author finel
 */
public abstract class Cnode extends CbasicFSMObject {
    protected Cpoint m_center;

    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public Cnode(Integer x, Integer y) {
        super();
        m_center = new Cpoint(x, y); // Cpoint() does null-check
    }
            
    /**
     * center getter
     * @return
     */
    public Cpoint getCenter() {
        return m_center;
    }
        
    /**
     * center setter
     * @param x X-coord
     * @param y Y-coord
     * @return true if success
     */
    public boolean setCenter(Integer x, Integer y) {
        if (x != null && y != null)
            m_center = new Cpoint(x, y);
        
        return x != null && y != null;
    }
    
    /**
     * center setter 
     * @param p Cpoint 
     * @return true if success
     */
    public boolean setCenter(Cpoint p) {
        if (p != null)
            m_center = p;
        
        return p != null;
    }
    
    @Override
    public boolean hasCoords(Integer x, Integer y) {
        if (x != null && y != null) {
            Ellipse2D e = new Ellipse2D.Double(m_center.m_x - Imisc.NODE_RADIUS,
                    m_center.m_y - Imisc.NODE_RADIUS, 2 * Imisc.NODE_RADIUS, 
                    2 * Imisc.NODE_RADIUS);
            int toleration = 5;
            int width = 10;
            int height = 10;
        
            return e.intersects(x - toleration, y - toleration, width, height);
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return Ifile.TYPE_NODE + Ifile.DELIMITER + m_center.toString() + 
                super.toString();
    }
    
    @Override
    public boolean equals (Object other) {
	if (other == null || ! this.getClass().equals(other.getClass()))
            return false;
        if (other == this)
            return true;
        
        Cnode n = (Cnode) other;        
        return m_center.equals(n.m_center) && m_label.equals(n.m_label);
    }

    @Override
    public int hashCode() {
        return Imisc.NODE_HASH;
    }
}