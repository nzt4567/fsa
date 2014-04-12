package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMmodel.CnodeAccept;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.CnodeStart;
import FSMBuilder.FSMmodel.CnodeStartAccept;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMmodel.CtransNormal;
import FSMBuilder.FSMview.View;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Handler for mouse left click.
 * @author kvasnict
 */

public class CmouseLeft extends Cmouse {

    /**
     * Default constructor
     * @param event mouse clicked event
     * @param fsm model
     * @param view view
     * @param node selected node
     */
    public CmouseLeft(MouseEvent event, Cmodel fsm, View view, Cnode node) {
        super(event, fsm, view, node);        
    }
    
    @Override
    public void click() {
        
        switch (m_modifiers) {
            case InputEvent.CTRL_DOWN_MASK:
                ctrl();
                break;
            case InputEvent.SHIFT_DOWN_MASK:
                shift();
                break;
            case InputEvent.ALT_DOWN_MASK:
                alt();
                break;
            case (InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK):
                ctrlshift();
                break;
            default:
                left();
        }
    }
    
    private void left() {
        Cnode n = m_fsm.getNode(m_x, m_y);
        
        if (n != null && m_selected != null) { // right click adds epsilon t.
            CtransNormal t = new CtransNormal(m_selected, n);
            m_fsm.addTransition(t);
        }
        else if (n != null && m_selected == null) {
            m_selected = n;
            m_view.selectNode(n);
        }
        else if (n == null) {
            m_selected = null;
            m_view.selectNode(n);
        }
        else
            throw new RuntimeException("LEFT CLICK FATAL ERROR");        
    }
    
    private void ctrl() {
        if (m_fsm.getNode(m_x, m_y) == null) { // no node @ (m_x,m_y)
            CnodeStart n = new CnodeStart(m_x, m_y);
            m_fsm.addNode(n);
        }
    }
    
    private void alt() {
        Cnode tmp_node = m_fsm.getNode(m_x, m_y);
        Ctrans tmp_trans = m_fsm.getTransition(m_x, m_y);
        
        if (tmp_trans != null)
            m_fsm.makeLabel(tmp_trans, m_view.editLabel());
        else if (tmp_node != null)
            m_fsm.makeLabel(tmp_node, m_view.editLabel());
        else {
            CnodeNormal n = new CnodeNormal(m_x, m_y);
            m_fsm.addNode(n);
        }
    }
    
    private void shift() {
        if (m_fsm.getNode(m_x, m_y) == null) {
            CnodeAccept n = new CnodeAccept(m_x, m_y);
            m_fsm.addNode(n);
        }
    } 

    private void ctrlshift() {
        if (m_fsm.getNode(m_x, m_y) == null) {
            CnodeStartAccept n = new CnodeStartAccept(m_x, m_y);
            m_fsm.addNode(n);
        }
    }
}