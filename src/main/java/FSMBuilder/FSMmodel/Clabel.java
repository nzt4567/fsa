package FSMBuilder.FSMmodel;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMfunctions.constants.Imisc;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for holding label-related information.
 * @author Logan Fine and kvasnict
 * @version 2
 */
public class Clabel {
    protected String m_label;
    protected final String m_subscript;
    protected final String m_superscript;
    protected List<String> m_transitions = null;

    /**
     * Default constructor
     */
    public Clabel() {
        m_label = "";
        m_subscript = "";
        m_superscript = "";
    }
    
    /**
     * Parameterized constructor
     * @param lab label
     * @param sup superscript
     * @param sub subscript
     */
    public Clabel(String lab, String sup, String sub) {
        if (lab != null)
            m_label = lab;
        else
            m_label = "";
        
        if (sup != null)
            m_superscript = sup;
        else
            m_superscript = "";
        
        if (sub != null)
            m_subscript = sub;
        else
            m_subscript = "";
    }
    
    public void parse_transition() {
        if (! "".equals(m_label)) {
            
            m_transitions = new ArrayList<String>();
            String[] t = m_label.split(Imisc.TRANS_DELIMITER);
        
            for (int i = 0; i < t.length; i++) {
                t[i] = t[i].trim();
                if (t[i].length() == 1)
                    m_transitions.add(t[i]);
            }
        
            if (m_transitions.isEmpty()) {
                m_transitions = null;
                m_label = "";
            } else {
                m_label = m_transitions.toString().replace("[", "").replace("]", "");
                m_label = m_label.replace(", ", Imisc.TRANS_DELIMITER);
            }
            
        }
    }
    
    /**
     * label getter
     * @return String
     */
    public String getLabel() {
        return m_label;
    }
    
    /**
     * subscript getter
     * @return String
     */
    public String getSubscript() {
        return m_subscript;
    }
    
    /**
     * superscript getter
     * @return String
     */
    public String getSuperscript() {
        return m_superscript;
    }
    
    public List<String> getTrans() {
        return m_transitions;
    }
    
    @Override
    public String toString() {
        return m_label + Ifile.DELIMITER + m_superscript + Ifile.DELIMITER + 
                m_subscript + Ifile.DELIMITER;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || ! this.getClass().equals(other.getClass()))
            return false;
        if (other == this)
            return true;
        
        Clabel l = (Clabel) other;
        
        return m_label.equals(l.m_label) && 
                m_superscript.equals(l.m_superscript) && 
                m_subscript.equals(l.m_subscript);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.m_label != null ? 
                this.m_label.hashCode() : 0);
        hash = 73 * hash + (this.m_subscript != null ? 
                this.m_subscript.hashCode() : 0);
        hash = 73 * hash + (this.m_superscript != null ? 
                this.m_superscript.hashCode() : 0);
        return hash;
    }
}