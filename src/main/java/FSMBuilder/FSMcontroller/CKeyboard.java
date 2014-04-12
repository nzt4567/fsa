package FSMBuilder.FSMcontroller;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMfunctions.fileIO.CexportImage;
import FSMBuilder.FSMfunctions.fileIO.dotFSM.Cloader;
import FSMBuilder.FSMfunctions.fileIO.dotFSM.Csaver;
import FSMBuilder.FSMfunctions.fileIO.dotTEX.CsaverLatex;
import FSMBuilder.FSMfunctions.simulator.Csimulator;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMview.View;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * Executes keyboard event handlers
 * @author kvasnict
 */
public class CKeyboard {

    /**
     * Mouse event
     */
    protected final KeyEvent m_event;

    /**
     * Model hodling the data
     */
    protected final Cmodel m_fsm;

    /**
     * View drawing to screen
     */
    protected final View m_view;

    /**
     * Pressed key code
     */
    protected final int m_key;

    /**
     * Simulation of the FSM for input string
     */
    protected final Csimulator m_simulator;
    
    /**
     * Default constructor
     * @param e Keyboard event - pressed button
     * @param m Model
     * @param v View
     * @param s Simulator
     */
    public CKeyboard(KeyEvent e, Cmodel m, View v, Csimulator s) {
        m_event = e;
        m_view = v;
        m_fsm = m;
        m_simulator = s;
        
        if (m_event != null)
            m_key = e.getKeyCode();
        else
            m_key = 0;
        
        if (m_view == null || m_fsm == null || m_simulator == null)
            throw new RuntimeException("NULL IN VIEW/MODEL/SIMULATOR");
    }
    
    /**
     * Method run with every pressed key deciding what should be done
     */
    public void press_key() {
        
        switch (m_key) {
            case Imisc.CHAR_S:
                s();
                break;
            case Imisc.CHAR_E:
                e();
                break;
            case Imisc.CHAR_L:
                l();
                break;
            case Imisc.CHAR_I:
                i();
                break;
            case Imisc.CHAR_X:
                x();
                break;
            case KeyEvent.VK_RIGHT:
                right();
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                up();
                break;
            case KeyEvent.VK_DOWN:
                break;
            default:
//                System.out.println("char code " + m_key);
                break;
        }
    }

    private void l() {
        File f = m_view.filename(Imisc.OPEN_FILE);
        if (f != null) {
            Cloader l = new Cloader(f, m_fsm);
            l.load();
        }
    }

    private void e() {
        CexportImage e = new CexportImage(m_view);
        e.export(Imisc.DEFAULT_IMG);
    }

    private void s() {
        File f = m_view.filename(Imisc.SAVE_FILE);
        
        if (f != null) {
            Csaver s = new Csaver(f);
        
            for (Cnode cnode : m_fsm.getNodes()) {
                cnode.acceptVisit(s);
            }
        
            for (Ctrans ctrans : m_fsm.getTransitions()) {
                ctrans.acceptVisit(s);
            }
        
            s.save();
        }
    }

    private void i() {
        if (m_fsm.getStartState() != null) {
            String i = m_view.inputDialog("Input string", "FSM simulator", 
                    JOptionPane.PLAIN_MESSAGE);
            m_simulator.setInput(i);
            m_simulator.reset();
            m_view.repaint();
        } else
            m_view.messageDialog("Please add a start state before input", 
                    "FSM simulator", JOptionPane.ERROR_MESSAGE);
    }
    
    private void right() {
        if (! m_simulator.hasDefaultInput()) {
            if (m_simulator.canSimulate()) {
                if (! m_simulator.next()) {
                    m_view.messageDialog("We are not at the end of the " + 
                            "string, however cannot go any further. Are you " +
                            "sure you have all the transitions labeled and " +
                            "there exists a state to which we can go to now?" + 
                            "\nCurrently reseting simulator, edit FSM/input " + 
                            "and start the simulation again.", 
                            "FSM simulator", JOptionPane.ERROR_MESSAGE);
                    m_simulator.reset();
                }
            
            } else if (m_simulator.accepted()) {
                m_view.messageDialog("String " + m_simulator.getInput() + 
                        " accepted!", "FSM simulator", 
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                m_view.messageDialog("String " + m_simulator.getInput() + 
                        " NOT accepted!", "FSM simulator", 
                        JOptionPane.WARNING_MESSAGE);
            }
            
            m_view.repaint();
        }
    }

    private void up() {
        m_simulator.reset();
        m_view.repaint();
        m_view.messageDialog("Simulator has been reset", "FSM simulator", 
                JOptionPane.PLAIN_MESSAGE);
    }
    
    private void x() {
        File f = m_view.filename(Imisc.SAVE_FILE);
        
        if (f != null) {
            CsaverLatex s = new CsaverLatex(f);
        
            for (Cnode cnode : m_fsm.getNodes()) {
                cnode.acceptVisit(s);
            }
        
            for (Ctrans ctrans : m_fsm.getTransitions()) {
                ctrans.acceptVisit(s);
            }
        
            s.save();
        }
    }
}