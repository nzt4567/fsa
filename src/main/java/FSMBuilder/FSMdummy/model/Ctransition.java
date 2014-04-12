package FSMBuilder.FSMdummy.model;

/**
 *
 * @author kvasnict
 * This is just a dummy testing class which should be completely rewritten.
 * I dont think the controller needs to call methods of this class directly - 
 * feel free to totally edit the API hoping it wont break things down.
 */

public class Ctransition {
    private final Cnode m_from;
    private final Cnode m_to;
    
    /**
     * Default constructor
     * @param from source node
     * @param to finish node
     */
    public Ctransition(Cnode from, Cnode to) {
        m_from = from;
        m_to = to;
    }
    
    /**
     * Check if this transition has those nodes
     * @param from source node
     * @param to finish node
     * @return true if yes
     */
    public boolean hasNodes(Cnode from, Cnode to) {
        return m_from.equals(from) && m_to.equals(to);
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (! this.getClass().equals(other.getClass()))
            return false;
        
        Ctransition t = (Ctransition) other;
        return t.hasNodes(m_from, m_to);
    }

    @Override
    public String toString() {
        return "[T: " + m_from.toString() + " -> " + m_to.toString() + "]";
    }
    
    // TODO: avoid getters/setters if possible

    /**
     * Get from node
     * @return Cnode
     */
        public Cnode getFrom() {
        return m_from;
    }
    
    // TODO: avoid getters/setters if possible

    /**
     * Get finish node
     * @return Cnode
     */
        public Cnode getTo() {
        return m_to;
    }
}