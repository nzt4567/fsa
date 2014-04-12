package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds coordinates of exactly one point.
 * @author Logan Fine and kvasnict
 * @version 2.5
 */
public final class Cpoint {
    public Integer m_x = null;
    public Integer m_y = null;
    
    /**
     * Default constructor
     * @param x x-coord
     * @param y y-coord
     */
    public Cpoint(Integer x, Integer y) {
        if (! setPosition(x, y))
            throw new RuntimeException("Cpoint creating failure - null");
    }
    
    /**
     * Set position of the point
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return true if success
     */
    public boolean setPosition(Integer x, Integer y) {
        if (x != null && y != null) {
            m_x = x;
            m_y = y;
        }
        
        return x != null && y != null;
    }
    
    /**
     * Return the coordinates of the point
     * @return List<Integer> such that (x, y) == (list[0], list[1])
     */
    public List<Integer> getPosition() {
        List<Integer> l = new ArrayList<Integer>();
        l.add(m_x);
        l.add(m_y);
        
        return l;
    }
    
    @Override
    public String toString() {
        return m_x + Ifile.DELIMITER + m_y + Ifile.DELIMITER;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (! this.getClass().equals(other.getClass()))
            return false;
        
        Cpoint p = (Cpoint) other;
        
        return m_x.equals(p.m_x) && m_y.equals(p.m_y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.m_x != null ? this.m_x.hashCode() : 0);
        hash = 13 * hash + (this.m_y != null ? this.m_y.hashCode() : 0);
        return hash;
    }
}