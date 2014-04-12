package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMview.View;
import java.awt.event.MouseEvent;

/**
 * Event handler for mouse dragging
 * @author kvasnict
 */
public class CmouseDrag extends Cmouse {

    /**
     * Default constructor
     * @param event dragging event
     * @param fsm model
     * @param view view 
     * @param selected selected node
     */
    public CmouseDrag(MouseEvent event, Cmodel fsm, View view, Cnode selected) {
        super(event, fsm, view, selected);        
    }
    
    @Override
    public void drag() {
        m_fsm.setNodeCenter(m_selected, m_x, m_y); //setNodeCenter checks null
    }
}
