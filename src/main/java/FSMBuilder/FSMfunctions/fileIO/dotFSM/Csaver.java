package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.CnodeAccept;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.CnodeStart;
import FSMBuilder.FSMmodel.CnodeStartAccept;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMmodel.CtransEps;
import FSMBuilder.FSMmodel.CtransNormal;
import FSMBuilder.FSMmodel.Ivisitor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles saving into .fsm file format
 * @author kvasnict
 */
public class Csaver implements Ivisitor {

    /**
     * File to save to
     */
    protected File m_file = null;

    /**
     * String to write to m_file
     */
    protected String m_toWrite;
    
    /**
     * Default constructor
     * @param f File to save to
     */
    public Csaver(File f) {
        m_toWrite = "";
        
        if (f != null)
            m_file = f;
        else
            throw new RuntimeException("File for saving is null");
    }
    
    /**
     * Write string to file
     * @return true if success
     */
    public boolean save() {
        if (! m_toWrite.equals("") && m_file != null)
            try {
                FileWriter w = new FileWriter(m_file, true);
                w.write(m_toWrite);
                w.close();
                return true;
            } catch (IOException ioe) {
                System.err.println("Writing .fsm failed: " + ioe.getMessage());
            }
        
        return false;
    }

    private String node(Cnode s) {
        return Ifile.TYPE_NODE + Ifile.DELIMITER + 
                s.getCenter().m_x + Ifile.DELIMITER + 
                s.getCenter().m_y + Ifile.DELIMITER + 
                s.getLabel().getLabel() + Ifile.DELIMITER + 
                s.getLabel().getSuperscript() + Ifile.DELIMITER + 
                s.getLabel().getSubscript() + Ifile.DELIMITER;
    }
    
    private String trans(Ctrans t) {
        return Ifile.TYPE_TRANS + Ifile.DELIMITER + 
                t.getFrom().getCenter().m_x + Ifile.DELIMITER +
                t.getFrom().getCenter().m_y + Ifile.DELIMITER +
                t.getTo().getCenter().m_x + Ifile.DELIMITER +
                t.getTo().getCenter().m_y + Ifile.DELIMITER +
                t.getLabel().getLabel() + Ifile.DELIMITER +
                t.getLabel().getSuperscript() + Ifile.DELIMITER +
                t.getLabel().getSubscript() + Ifile.DELIMITER;
    }
    
    @Override
    public void visit(CnodeAccept s) {
        m_toWrite += node(s) + Ifile.SUB_N_ACCEPT + "\n";
    }

    @Override
    public void visit(CnodeNormal s) {
        m_toWrite += node(s) + Ifile.SUB_N_NORMAL + "\n";
    }

    @Override
    public void visit(CnodeStart s) {
        m_toWrite += node(s) + Ifile.SUB_N_START + "\n";
    }

    @Override
    public void visit(CnodeStartAccept s) {
        m_toWrite += node(s) + Ifile.SUB_N_STARTACCEPT + "\n";
    }

    @Override
    public void visit(CtransNormal t) {
        m_toWrite += trans(t) + Ifile.SUB_T_NORMAL + "\n";
    }

    @Override
    public void visit(CtransEps t) {
        m_toWrite += trans(t) + Ifile.SUB_T_EPS + "\n";
    }
}