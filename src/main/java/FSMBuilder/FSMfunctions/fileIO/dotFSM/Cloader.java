package FSMBuilder.FSMfunctions.fileIO.dotFSM;

import FSMBuilder.FSMfunctions.constants.Ifile;
import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Cmodel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class handling loading .fsm files
 * @author kvasnict
 */
public class Cloader {

    /**
     * File to load things from
     */
    protected final File m_file;

    /**
     * Model
     */
    protected final Cmodel m_fsm;

    /**
     * Node loader
     */
    protected final CloadNode[] m_ln;

    /**
     * Transition loader
     */
    protected final CloadTrans[] m_lt;

    /**
     * Subtype of trans/node
     */
    protected Integer m_subtype;
    
    /**
     * Default constructor
     * @param f file to load from
     * @param m model to load the data into
     */
    public Cloader(File f, Cmodel m) {
        if (m == null || f == null)
            throw  new RuntimeException("MODEL/FILE NULL IN LOADER");
        
        m_file = f;
        m_fsm = m;
        m_ln = new CloadNode[Imisc.NODE_SUBTYPES];
        m_lt = new CloadTrans[Imisc.TRANSITION_SUBTYPES];
    }

    /**
     * Load the file
     */
    public void load() {
        BufferedReader br = null;
        String line;
        
        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(m_file)));
            
            if (br == null)
                throw new RuntimeException("LOADING FILE FAILED");
                
            m_fsm.clear();
            while ((line = br.readLine()) != null) {
                String[] rec = line.split(Ifile.DELIMITER);
                Integer type = new Integer(rec[Ifile.IDX_TYPE]);
                
                if (type.equals(Ifile.TYPE_NODE)) { //TODO: check if rec ma dost polozek
                    m_ln[Ifile.SUB_N_NORMAL] = new CloadNNormal(rec);
                    m_ln[Ifile.SUB_N_START] = new CloadNStart(rec);
                    m_ln[Ifile.SUB_N_ACCEPT] = new CloadNAccept(rec);
                    m_ln[Ifile.SUB_N_STARTACCEPT] = new CloadNStartAccept(rec);
                    m_subtype = new Integer (rec[Ifile.IDX_N_SUBTYPE]);
                    
                    m_ln[m_subtype].execute(m_fsm);
                } else if (type.equals(Ifile.TYPE_TRANS)) {
                    m_lt[Ifile.SUB_T_NORMAL] = new CloadTransNormal(rec, m_fsm);
                    m_lt[Ifile.SUB_T_EPS] = new CloadTransEps(rec, m_fsm);
                    m_subtype = new Integer (rec[Ifile.IDX_T_SUBTYPE]);
                    
                    m_fsm.addTransition(m_lt[m_subtype].execute());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cloader.class.getName()).log(Level.SEVERE, 
                    null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cloader.class.getName()).log(Level.SEVERE, 
                    null, ex);
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                Logger.getLogger(Cloader.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
}