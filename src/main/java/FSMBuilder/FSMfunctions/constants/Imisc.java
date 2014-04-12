package FSMBuilder.FSMfunctions.constants;

/**
 * Holds constants used in various packages
 * @author kvasnict
 */
public interface Imisc {

    /**
     * shape of node in Cnode class - round
     */
    public final static Integer NODE_ROUND = 0;
    
    /**
     * node radius
     */
    public final static Integer NODE_RADIUS = 20;
    
    /**
     * number of subtypes of nodes
     */
    public final static Integer NODE_SUBTYPES = 4;
    
    /**
     * number of subtypes of transitions
     */
    public final static Integer TRANSITION_SUBTYPES = 2;
    
    /**
     * offset in pixels for the second circle of accept node
     */
    public final static Integer ACCEPT_NODE_OFFSET = 5;
    
    /**
     * length of line in pixels pointing to a start node
     */
    public final static Integer START_NODE_LINE_LEN = 25;
    
    /**
     * default font size for labels
     */
    public final static Integer FONT_SIZE = 16;
    
    /**
     * default font for labels
     */
    public final static String FONT = "Courier";
    
    /**
     * default separator between transitions in transition label
     */
    public final static String TRANS_SEPARATOR = ",";
    
    /**
     * hash for all nodes used by hashcode()
     */
    public final static int NODE_HASH = 2331;
    
    /**
     * hash for all transmissions used by hashcode()
     */
    public final static int TRANS_HASH = 5745;
    
    /**
     * default image filetype
     */
    public final static String DEFAULT_IMG = "png";
    
    /**
     * save file code
     */
    public final static char SAVE_FILE = 's';
    
    /**
     * open file code
     */
    public final static char OPEN_FILE = 'o';
    
    /**
     * set input code
     */
    public final static char SET_INPUT = 'i';
    
    /**
     * Default simulator input
     */
    public final static String DEFAULT_INPUT = "Press 'i' to set input!";
    
    /**
     * Index of the first char in input
     */
    public final static Integer FIRST_CHAR = 0;
    
    /**
     * Start string of the input
     */
    public final static String INPUT_START = "Simulator input: ";
        
    /**
     * Transition delimiter
     */
    public final static String TRANS_DELIMITER = ",";
    
    /**
     * Char code for S
     */
    public final static int CHAR_S = 83;
    
    /**
     * Char code for E
     */
    public final static int CHAR_E = 69;
    
    /**
     * Char code for L
     */
    public final static int CHAR_L = 76;
    
    /**
     * Char code for I
     */
    public final static int CHAR_I = 73;
    
    /**
     * Char code for X
     */
    public final static int CHAR_X = 88;
    
    /**
     * Greek letter epsilon - has to be changed, * is temporary
     */
    public final static String GREEK_EPS = "*";
}