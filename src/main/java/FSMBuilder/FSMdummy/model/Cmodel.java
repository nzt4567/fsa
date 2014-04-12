package FSMBuilder.FSMdummy.model;

import FSMBuilder.FSMmodel.ImodelListener;
import FSMBuilder.FSMcontroller.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kvasnict
 * This is just a dummy testing class which should be completely rewritten.
 * Unless stated the methods and their parameters should stay the same BUT
 * the logic behind them will be different - more complex. AND it is very likely
 * that we will need some additional methods that are not here at ALL.
 */

public class Cmodel {
    
    private final List<Cnode> m_nodes;
    private final List<Ctransition> m_transitions;
    private final List<ImodelListener> m_listeners;

    /**
     * Default constructor
     */
    public Cmodel() {
        m_nodes = new ArrayList<Cnode>();
        m_transitions = new ArrayList<Ctransition>();
        m_listeners = new ArrayList<ImodelListener>();
    }
    
    /**
     * Add a node
     * @param node node to add
     * @return true if successful
     */
    public boolean addNode(Cnode node) {
        // does not check for existing nodes, overlapping nodes, ... 
        m_nodes.add(node);
        notifyListeners();
        return true;
    }
    
    /**
     * Add a transition
     * @param from source node
     * @param to finish node
     * @return true if successful
     */
    public boolean addTransition(Cnode from, Cnode to) {
        // same as node
        Ctransition t = new Ctransition(from, to);
        m_transitions.add(t);
        notifyListeners();
        return true;
    }
    
    /**
     * Search for a node at selected coords
     * @param x X-coord
     * @param y Y-coord
     * @return node if exists, null otherwise
     */
    public Cnode getNode(Integer x, Integer y) {
        for (Cnode n : m_nodes)
            if (n.hasCoords(x, y))
                return n;
        
        return null;
    }
    
    /**
     * Never used cause of different API
     * @param from source node
     * @param to finish node
     * @return Ctransition if exists, null otherwise
     */
    public Ctransition getTransition(Cnode from, Cnode to) {
        // API change - public Ctransition getTransition(Integer x, Integer y)
        for (Ctransition t : m_transitions)
            if (t.hasNodes(from, to))
                return t;
        
        return null;
    }
    
    /**
     * Delete a node
     * @param n node 
     */
    public void delNode(Cnode n) {
        // checking for existance at least, probably something else too
        m_nodes.remove(n);
        notifyListeners();
    }
    
    /**
     * Delete a transition
     * @param t transition
     */
    public void delTransition(Ctransition t) {
        // same as delNode
        m_transitions.remove(t);
        notifyListeners();
    }
    
    private boolean hasNode(Cnode n) {
        for (Cnode cnode : m_nodes)
            if (n.equals(cnode))
                return true;
        
        return false;
    }
    
    private boolean hasTransition(Ctransition t) {
        for (Ctransition ctransition : m_transitions)
            if (t.equals(ctransition))
                return true;
        
        return false;
    }
    
    /**
     * Iterable over all the nodes
     * @return Iterable<Cnode>
     */
    public Iterable<Cnode> getNodes() {
        // dnt know if view won't need this differently ... 
        return m_nodes;
    }
    
    /**
     * Iterable over all the transitions
     * @return Iterable<Ctransition>s
     */
    public Iterable<Ctransition> getTransitions() {
        // ... I am using a super trivial view so it is enough for testing
        return m_transitions;
    }
    
    /**
     * Observer pattern - listener
     * @param l listener
     * @return true if success
     */
    public boolean addListener(ImodelListener l) {
        // false in case of too many listeners
        m_listeners.add(l);
        
        return true;
    }
    
    /**
     * Observer pattern - listener
     * @param l listener
     * @return true if success
     */
    public boolean delListener(ImodelListener l) {
        // false if listener didnt exist/something else got fucked
        m_listeners.remove(l);
        
        return true;
    }
    
    private void notifyListeners() {
        for (ImodelListener l : m_listeners)
            l.update();
    }
    
    @Override
    public String toString() {
        // console output is totally just for testing
        String ret = "FINITE STATE MACHINE\n\tNODES:\t";
        
        for (Cnode n : m_nodes) {
            ret += n.toString() + " ";
        }
        ret += "\n\tTRANSITIONS:\t";
        
        for (Ctransition t : m_transitions) {
            ret += t.toString() + " ";
        }
        ret += "\n\n";
        
        return ret;
    }
    
    @Override
    public boolean equals(Object other) {
        // not sure if this even works correctly, i dnt think it gets used @ all
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (! this.getClass().equals(other.getClass()))
            return false;
        
        Cmodel fsm = (Cmodel) other;
        
        if (m_nodes.size() != fsm.m_nodes.size() || 
                m_transitions.size() != fsm.m_transitions.size())
            return false;
        
        for (Cnode n : m_nodes)
            if (! fsm.hasNode(n))
                return false;
        
        for (Ctransition t : m_transitions)
            if (! fsm.hasTransition(t))
                return false;
        
        return true;
    }

    @Override
    public int hashCode() {
        // autogenerated by netbeans
        int hash = 3;
        hash = 41 * hash + (this.m_nodes != null ? 
                this.m_nodes.hashCode() : 0);
        hash = 41 * hash + (this.m_transitions != null ? 
                this.m_transitions.hashCode() : 0);
        return hash;
    }
}