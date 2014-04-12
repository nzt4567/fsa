package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMview.View;
import java.awt.event.MouseEvent;

/**
 * Handler for mouse right click
 * @author kvasnict
 */

public class CmouseRight extends Cmouse {

    /**
     * Default constructor
     * @param event mouse clicked event
     * @param fsm model
     * @param view view
     * @param node selected node
     */
    public CmouseRight(MouseEvent event, Cmodel fsm, View view, Cnode node) {
        super(event, fsm, view, node);
    }
    
    @Override
    public void click() {
        right();
    }
    
    private void right() {
        Cnode n = m_fsm.getNode(m_x, m_y);
        Ctrans t = m_fsm.getTransition(m_x, m_y);

        if (t != null) {
            m_fsm.delTransition(t);
        }
        else if (n != null) {
            if (n.equals(m_selected)) {
                m_selected = null;
                m_view.selectNode(null);
            }
            m_fsm.delNode(n);
        }
    }
}