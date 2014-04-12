package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMmodel.ImodelListener;
import FSMBuilder.FSMfunctions.simulator.Csimulator;
import FSMBuilder.FSMview.View;
import FSMBuilder.FSMmodel.Cmodel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author kvasnict
 * This class handles all the input a decides what to do with it. Decides
 * means creates other classes that handle the type of input that was generated.
 * It can and will get changed - but you should not need to worry about that.
 */

public class Ccontrol implements ImodelListener, MouseListener, 
        MouseMotionListener, KeyListener {
        private final View m_view;
        private final Cmodel m_model;
        private Cmouse m_mouse;
        private CKeyboard m_keyboard;
        private final Csimulator m_simulator;
        
    /**
     * Default constructor
     * @param view View
     * @param model Model
     */
    public Ccontrol(View view, Cmodel model) {
        m_view = view;
        m_model = model;
        m_simulator = new Csimulator(m_model);
        m_mouse = new Cmouse(null, m_model, m_view, null);
        m_keyboard = new CKeyboard(null, m_model, m_view, m_simulator);
        
        setup();
    }
    
    private void setup() {
        m_view.addMouseListener(this);
        m_view.addMouseMotionListener(this);
        m_view.getFrame().addKeyListener(this);
        m_view.setSimulator(m_simulator);
        m_model.addListener(this);
    }

    @Override
    public void update() {
        m_simulator.reset();
        m_view.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        
        if (button == MouseEvent.BUTTON1)
            m_mouse = new CmouseLeft(e, m_model, m_view, m_mouse.m_selected);
        else if (button == MouseEvent.BUTTON3) // ?!? not button2 ?!?
            m_mouse = new CmouseRight(e, m_model, m_view, m_mouse.m_selected);
        
        m_mouse.click();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        m_mouse = new CmouseDrag(e, m_model, m_view, m_mouse.m_selected);
        m_mouse.drag();
    }

    @Override
    public void mouseMoved(MouseEvent e)    {}
    
    @Override
    public void mousePressed(MouseEvent e)  {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e)  {}

    @Override
    public void mouseExited(MouseEvent e)   {} 

    @Override
    public void keyTyped(KeyEvent e) {
        m_keyboard = new CKeyboard(e, m_model, m_view, m_simulator);
        m_keyboard.press_key();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        m_keyboard = new CKeyboard(e, m_model, m_view, m_simulator);
        m_keyboard.press_key();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}