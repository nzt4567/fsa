package FSMBuilder.FSMdummy.model;

/**
 *
 * @author kvasnict
 * This is just a dummy testing class which should be completely rewritten.
 * I dont think the controller needs to call methods of this class directly - 
 * feel free to totally edit the API hoping it wont break things down.
 */

public class Cnode {
    protected final Integer m_x;
    protected final Integer m_y;
    
    /**
     * Default constructor
     * @param x X-coord
     * @param y Y-coord
     */
    public Cnode(Integer x, Integer y) {
        m_x = x;
        m_y = y;
    }
    
    /**
     * Check if there is node at those coords 
     * @param x X-coord
     * @param y Y-coord
     * @return true if yes
     */
    public boolean hasCoords(Integer x, Integer y) {
        int offset = 7;
        int left_x = m_x - offset;
        int right_x = m_x + offset;
        int up_y = m_y - offset;
        int down_y = m_y + offset;
        
        if (left_x < 0)
            left_x = 0;
        
        if (up_y < 0)
            up_y = 0;
        
        if (x >= left_x && x <= right_x && y >= up_y && y <= down_y)
            return true;
        
        return false;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (! this.getClass().equals(other.getClass()))
            return false;
        
        Cnode n = (Cnode) other;
        return n.hasCoords(m_x, m_y);
    }
    
    @Override
    public String toString() {
        return "[N] (" + m_x + ", " + m_y + ") ";
    }
    
    // TODO: avoid getters/setters if possible

    /**
     * Get x-coord
     * @return int
     */
        public int getX() {
        return m_x;
    }
    
    // TODO: avoid getters/setters if possible

    /**
     * Get y-coord
     * @return int
     */
        public int getY() {
        return m_y;
    }
}