package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMview.View;
import java.awt.event.MouseEvent;

/**
 * Executes mouse event handlers
 * @author kvasnict
 */

public class Cmouse {

    /**
     * Selected node - marked red
     */
    protected Cnode m_selected;

    /**
     * Mouse event
     */
    protected final MouseEvent m_event;

    /**
     * Keys like ctrl/alt/shift hold during click
     */
    protected final int m_modifiers;

    /**
     * Model
     */
    protected final Cmodel m_fsm;

    /**
     * X-coord of the click
     */
    protected final int m_x;

    /**
     * Y-coord of the click
     */
    protected final int m_y;

    /**
     * View
     */
    protected final View m_view;

    /**
     * Default constructor 
     * @param event mouse clicked event
     * @param fsm model
     * @param view view 
     * @param selected selected node
     */
    public Cmouse(MouseEvent event, Cmodel fsm, View view, Cnode selected) {
        m_event = event; // this is allowed to be null
        m_selected = selected; // this too
        
        if (m_event != null) {
            m_modifiers = m_event.getModifiersEx();
            m_x = m_event.getX();
            m_y = m_event.getY();
        } else { // defaults (used when CMouse created without actual click)
            m_modifiers = 0;
            m_x = -1;
            m_y = -1;
        }
        if (fsm == null || view == null)
            throw new RuntimeException("NULL IN VIEW/MODEL");
        else {
            m_fsm = fsm;
            m_view = view;
        }
    }
    
    /**
     * Run when clicked
     */
    public void click() {}

    /**
     * Run when dragged
     */
    public void drag() {}
}