package FSMBuilder.FSMmodel;

/**
 * Dummy class for holding every object that can appear in the model - 
 * state or a transition so far
 * @author kvasnict
 */
public abstract class CbasicFSMObject implements IcommonElement {
    protected Clabel m_label = null;
    
    /**
     * Default constructor
     */
    public CbasicFSMObject() {
        m_label = new Clabel();
    }
    
    @Override
    public Clabel getLabel() {
        return m_label;
    }
    
    @Override
    public boolean setLabel(Clabel l) {
        if (l != null)
            m_label = l;
        
        return  l != null;
    }
    
    @Override
    public String toString() {
        return m_label.toString();
    }
}