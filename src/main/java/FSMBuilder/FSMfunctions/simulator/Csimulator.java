package FSMBuilder.FSMfunctions.simulator;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Ctrans;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kvasnict
 */
public class Csimulator {
    protected Set<Cnode> m_actualStates;
    protected String m_input;
    protected Integer m_inputIdx;
    protected Cmodel m_fsm;
    
    public Csimulator(Cmodel m) {
        m_actualStates = new HashSet<Cnode>();
        m_input = Imisc.DEFAULT_INPUT;
        m_inputIdx = Imisc.FIRST_CHAR;
        m_fsm = m;
    }
    
    public String getInput() {
        return m_input;
    }
    
    public boolean setInput(String s) {
        if (s != null)
            m_input = s;
        
        return s != null;
    }
    
    public Integer getInputIdx() {
        return m_inputIdx;
    }
    
    public boolean setInputIdx(Integer i) {
        if (i != null)
            m_inputIdx = i;
        
        return i != null;
    }
    
    public Set<Cnode> getStates() {
        return m_actualStates;
    }
    
    public boolean setStates(Set<Cnode> s) {
        if (s != null)
            m_actualStates = s;
        
        return s != null;
    }
    
    public boolean canSimulate() {
        return (! m_input.equals(Imisc.DEFAULT_INPUT)) && 
                (m_inputIdx < m_input.length());
    }
    
    public boolean hasDefaultInput() {
        return m_input.equals(Imisc.DEFAULT_INPUT);
    }
    
    public void addByLetter(Cnode n, Set<Cnode> set) {
        set.add(n);

        for (Ctrans t: m_fsm.getOutgoingTrans(n)) {
            if (t.getLabel().getTrans() != null) {    
                for (String s : t.getLabel().getTrans()) {
                    if (s.equals(Imisc.GREEK_EPS)) {
                        addByLetter(t.getTo(), set);
                    }
                }
            }
        }
    }
    
    public void addByEps(Cnode n, Set<Cnode> set) {
        set.add(n);
        
        for (Ctrans t: m_fsm.getOutgoingTrans(n)) {
            if (t.getLabel().getTrans() != null) {
                for (String s : t.getLabel().getTrans()) {
                    if (s.equals(Imisc.GREEK_EPS)) {
                        addByEps(t.getTo(), set);
                    } else if (s.equals(m_input.substring(m_inputIdx, 
                            m_inputIdx + 1))) {
                        addByLetter(t.getTo(), set);
                    }
                }
            }
        }
    }
    
    public boolean next() {
        Set<Cnode> tmp = new HashSet<Cnode>();
        boolean atLeastOneTrans = false;
        boolean atLeastOneNode = false;
        
        if (canSimulate()) {
            for (Cnode n : m_actualStates) {
                for (Ctrans t : m_fsm.getOutgoingTrans(n)) {
                    if (t.getLabel().getTrans() != null) {
                        atLeastOneTrans = true;
                        for (String s : t.getLabel().getTrans()) {
                            if (s.equals(m_input.substring(m_inputIdx, 
                                    m_inputIdx + 1))) {
                                if (! m_actualStates.contains(t.getTo())) {
                                    atLeastOneNode = true;
                                    addByLetter(t.getTo(), tmp);
                                }
                            } else if (s.equals(Imisc.GREEK_EPS)) {
                                if (! m_actualStates.contains(t.getTo())) {
                                    atLeastOneNode = true;
                                    addByEps(t.getTo(), tmp);
                                }
                            }
                        }
                    }
                }
            }
            m_inputIdx++;
            m_actualStates = tmp;
        }
        
        return atLeastOneTrans && atLeastOneNode;
    }
    
    public boolean accepted() {
        Set<Cnode> intersection = new HashSet<Cnode>(m_actualStates);
        intersection.retainAll(m_fsm.getAccept());
        
        return ! intersection.isEmpty();
    }
    
    public void reset() {
        if (m_fsm.getStartState() != null) {
            m_actualStates = new HashSet<Cnode>(m_fsm.getStartState());
            for (Cnode cnode : m_actualStates) {
                addByLetter(cnode, m_actualStates);
                break;
            }
        } else {
            m_actualStates = new HashSet<Cnode>();
            m_input = Imisc.DEFAULT_INPUT;
        }
        m_inputIdx = Imisc.FIRST_CHAR;
    }
}