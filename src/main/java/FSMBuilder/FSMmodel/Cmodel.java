package FSMBuilder.FSMmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Holds the model - states, transitions and listeners
 * @author Logan Fine
 * @version 3
 */

public class Cmodel {
//    protected final Set<Cnode> m_nodes; // fsm states
    protected final Set<Ctrans> m_transitions; // fst transitions
    protected final Set<Cnode> m_stateNormal;
    protected final Set<Cnode> m_stateStart;
    protected final Set<Cnode> m_stateAccept;
    protected final List<ImodelListener> m_listeners; // model observers
    protected final int m_MAXLISTENERS = 42; // why? cause 42 is the answer ...

    /**
     * Default constructor
     */
    public Cmodel() {
//        m_nodes = new HashSet<Cnode>();
        m_stateNormal = new HashSet<Cnode>();
        m_stateStart  = new HashSet<Cnode>();
        m_stateAccept = new HashSet<Cnode>();
        m_transitions = new HashSet<Ctrans>();
        m_listeners = new ArrayList<ImodelListener>();
    }
    
    private Set<Cnode> union() {
        Set<Cnode> u = new HashSet<Cnode>(m_stateAccept);
        u.addAll(m_stateNormal);
        u.addAll(m_stateStart);
        
        return u;
    }
    
    private boolean commonNodeAdd(Set<Cnode> s, Cnode n) {
        if (n != null && s != null && s.add(n)) {
            return notifyListeners();
        }
        
        return false;
    }
        
     /**
     * Tries to add a state to the model && notify listeners
     * @param node state to be added
     * @return true if success
     */
    public boolean addNode(CnodeNormal node) {
        return commonNodeAdd(m_stateNormal, node);
    }
    
    public boolean addNode(CnodeStart node) {
        return m_stateStart.size() < 1 && commonNodeAdd(m_stateStart, node); 
    }
    
    public boolean addNode(CnodeAccept node) {
        return commonNodeAdd(m_stateAccept, node);
    }
        
    public boolean addNode(CnodeStartAccept node) {
        return m_stateStart.size() < 1 && commonNodeAdd(m_stateStart, node) &&
                commonNodeAdd(m_stateAccept, node);
    }
    
    /**
     * Tries to remove a node from the FSM
     * @param n node to remove
     * @return true if success
     */
    public boolean delNode(Cnode n) {
        if (hasNode(n)) { // hasNode() takes care of null
            List<Ctrans> toDel = new ArrayList<Ctrans>();
            
            for (Ctrans t : m_transitions)
                if (t.m_from.equals(n) || t.m_to.equals(n))
                    toDel.add(t); // never ever edit list while iterating, ...
            for (Ctrans t : toDel)
                delTransition(t); // ... be nice and do it afterwards

            m_stateAccept.remove(n);
            m_stateNormal.remove(n);
            m_stateStart.remove(n);
            return notifyListeners();
        }
        return false;
    }

    
    /**
     * Tries to add a transition
     * @param t transition
     * @return true if success
     */
    public boolean addTransition(Ctrans t) {
        if (t != null && hasNode(t.getFrom()) && 
                hasNode(t.getTo()) && m_transitions.add(t)) {
            notifyListeners();            
            return true;
        }
        
        return false;
    }
    
    /**
     * Tries to remove a transition from the FSM
     * @param t transition to remove
     * @return true if success
     */
    public boolean delTransition(Ctrans t) {
        if (hasTransition(t)) { // hasTransition takes care of null
            m_transitions.remove(t);
            notifyListeners();
            
            return true;
        }
        
        return false;
    }

    /**
     * Searches for a node in the FSM
     * @param n node to find
     * @return true if node exists
     */
    public boolean hasNode(Cnode n) {
        Set<Cnode> u = union();
        
        return n != null && u.contains(n);
    }
    
    /**
     * Searches for a transition in the FSM
     * @param t transition to find
     * @return true if the transition is in the model
     */
    public boolean hasTransition(Ctrans t) {
        return t != null && m_transitions.contains(t);
    }
    
    /**
     * Search for a node that has at least some part of it at coords x,y
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Cnode if such node exists, null otherwise
     */
    public Cnode getNode(Integer x, Integer y) {
        Set<Cnode> u = union();
        
        for (Cnode n : u)
            if (n.hasCoords(x, y)) // hasCoords takes care of null
                return n;
        
        return null;
    }
    
    /**
     * Search for a transition that has at least some part of it at coords x,y
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return Ctrans if such transition exists, null otherwise
     */
    public Ctrans getTransition(Integer x, Integer y) {
        for (Ctrans t : m_transitions)
            if (t.hasCoords(x, y)) // hasCoords takes care of null
                return t;
        
        return null;
    }
            
    /**
     * Iterate through all the states in the FSM
     * @return m_nodes
     */
    public Set<Cnode> getNodes() {
        Set<Cnode> u = union();
        
        return u;
    }
    
    public Set<Cnode> getAccept() {
        return m_stateAccept;
    }
    
    /**
     * Iterate through all the transitions in the FSM
     * @return m_transitions
     */
    public Set<Ctrans> getTransitions() {
        return m_transitions;
    }
    
    /**
     * Set a label for node/transition
     * @param d node/transition
     * @param l label
     * @return true if success
     */
    public boolean makeLabel(CbasicFSMObject d, Clabel l) {
        if (d != null) // setLabel takes care of null
            return d.setLabel(l) && notifyListeners();
            
        return false;
    }
    
    /**
     * Change the center of the node
     * @param n node
     * @param x new x-coord
     * @param y new y-coord
     * @return true if success
     */
    public boolean setNodeCenter(Cnode n, Integer x, Integer y) {
        if (n != null) // setCenter takes care of null
            return n.setCenter(x, y) && notifyListeners();
        
        return false;
    }
    
    /**
     * Change the center of the node
     * @param n node
     * @param p Cpoint
     * @return true if success
     */
    public boolean setNodeCenter(Cnode n, Cpoint p) {
        if ( n != null) // setCenter takes care of null
            return n.setCenter(p) && notifyListeners();
        
        return false;
    }
    
    
    public Iterable<Ctrans> getOutgoingTrans(Cnode n) {
        Set<Ctrans> ret = new HashSet<Ctrans>();
        
        for (Ctrans ctrans : m_transitions) {
            if (ctrans.m_from.equals(n))
                ret.add(ctrans);
        }
        
        return ret;
    }
    
    public Iterable<Ctrans> getIncomingTrans(Cnode n) {
        Set<Ctrans> ret = new HashSet<Ctrans>();
        
        for (Ctrans ctrans : m_transitions) {
            if (ctrans.m_to.equals(n))
                ret.add(ctrans);
        }
        
        return ret;
    }
      
    /**
     * Will add a listener to the model to be notified
     * @param l listener to be added
     * @return true if success
     */
    public boolean addListener(ImodelListener l) {
        if ((m_MAXLISTENERS >= m_listeners.size() || m_MAXLISTENERS == -1) &&
               (l != null) && (! m_listeners.contains(l)) ) {
            m_listeners.add(l);
            return true;
        }
        
        return false;
    }
    
    /**
     * Will remove the listener from being notified
     * @param l listener to be removed
     * @return true if success
     */
    public boolean delListener(ImodelListener l) {
        return m_listeners.remove(l);        
    }
    
    /**
     * Call update() method of all the observers of this model
     */
    private boolean notifyListeners() {
        for (ImodelListener l : m_listeners)
            l.update();
        
        return true;
    }
    
    public void clear() {
        m_stateNormal.clear();
        m_stateStart.clear();
        m_stateAccept.clear();
        m_transitions.clear();
    }
        
    public Set<Cnode> getStartState() {
        if (! m_stateStart.isEmpty())
            return m_stateStart;
        
        return null;
    }

    @Override
    public String toString() {
        String ret = "";
        Set<Cnode> u = union();
        
        for (Cnode cnode : u)
            ret += cnode.toString();
        
        for (Ctrans ctransition : m_transitions)
            ret += ctransition.toString();
        
        return ret;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || ! this.getClass().equals(other.getClass()))
            return false;
        if (other == this)
            return true;
        
        Cmodel fsm = (Cmodel) other;
        Set<Cnode> u = union();
        
        if (m_stateAccept.size() != fsm.m_stateAccept.size() ||
                m_stateNormal.size() != fsm.m_stateNormal.size() ||
                m_stateStart.size() != fsm.m_stateStart.size() ||
                m_transitions.size() != fsm.m_transitions.size())
            return false;
        
        for (Cnode n : u)
            if (! fsm.hasNode(n))
                return false;
        
        for (Ctrans t : m_transitions)
            if (! fsm.hasTransition(t))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 47 * hash + (this.m_transitions != null ? 
                this.m_transitions.hashCode() : 0);
        hash = 47 * hash + (this.m_stateNormal != null ? 
                this.m_stateNormal.hashCode() : 0);
        hash = 47 * hash + (this.m_stateStart != null ? 
                this.m_stateStart.hashCode() : 0);
        hash = 47 * hash + (this.m_stateAccept != null ? 
                this.m_stateAccept.hashCode() : 0);
        
        return hash;
    }
}