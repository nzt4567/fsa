package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMfunctions.simulator.Csimulator;
import FSMBuilder.FSMmodel.Ivisitor;
import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.Ctrans;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Draws everything to the screen
 * @author Duri Abdurahman Duri
 */

public class View extends JPanel {
    private static final int m_WIDTH = 900;
    private static final int m_HEIGHT = 900;
    private final Cmodel m_fsm;
    private Cnode m_selected; // tohle bych mel mit jenom tady a mit na to getter/setter do ccontrol
    private final JFrame m_frame;
    private Csimulator m_simulator;
    
    /**
     * Default constructor
     * @param model mode
     * @param f frame
     */
    public View(Cmodel model, JFrame f) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        m_fsm = model;
        m_selected = null;
        m_frame = f;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(m_WIDTH, m_HEIGHT);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        Ivisitor v = new VisitorPainter(g);
        PainterInput i = new PainterInput(g, m_simulator);
        i.drawAll();
        
        g.setColor(Color.BLACK);
        for (Cnode cnode : m_fsm.getNodes()) {
            cnode.acceptVisit(v);
        }
            
        for (Ctrans ctransition : m_fsm.getTransitions()) {
            ctransition.acceptVisit(v);
        }
        
        if (m_selected == null) {
            g.setColor(Color.BLUE);
            for (Cnode cnode : m_simulator.getStates()) {
                cnode.acceptVisit(v);
            }
        } else {
            g.setColor(Color.RED);
            m_selected.acceptVisit(v);
        }
        g.setColor(Color.BLACK);
    }
    
    /**
     * Select selected node
     * @param n node
     */
    public void selectNode(Cnode n) {
        m_selected = n;
        repaint();
    }
        
    /**
     * Label editor
     * @return Clabel
     */
    public Clabel editLabel() {
        String lab = inputDialog("Set label", "LABEL EDITOR", 
                JOptionPane.PLAIN_MESSAGE);
        String sup = inputDialog("Set superscript", "LABEL EDITOR", 
                JOptionPane.PLAIN_MESSAGE);
        String sub = inputDialog("Set subscript", "LABEL EDITOR", 
                JOptionPane.PLAIN_MESSAGE);
                
        return new Clabel(lab, sup, sub);
    }
    
    /**
     * frame getter
     * @return JFrame
     */
    public JFrame getFrame() {
        return m_frame;
    }
    
    /**
     * Filename chooser
     * @param mode 'o' for open, 's' for saving
     * @return File if success, else null
     */
    public File filename(char mode) {
        JFileChooser fc = new JFileChooser();
        if (mode == Imisc.OPEN_FILE) {
            fc.showOpenDialog(this);
            
            return fc.getSelectedFile();
        } else if (mode == Imisc.SAVE_FILE) {
            fc.showSaveDialog(this);
            
            return fc.getSelectedFile();
        }
        
        return null;
    }
    
    public void messageDialog(String text, String title, int type) {
        if (text != null && title != null) {
            JOptionPane.showMessageDialog(m_frame, text, title, type);
        }
    }
    
    public String inputDialog(String text, String title, int type) {
        return (String) JOptionPane.showInputDialog(m_frame, text, title,
                type, null, null, null);
    }
    
    public boolean setSimulator(Csimulator s) {
        if (s != null)
            m_simulator = s;
        
        return s != null;
    }
}