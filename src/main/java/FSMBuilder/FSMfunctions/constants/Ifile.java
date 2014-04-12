package FSMBuilder.FSMfunctions.constants;

/**
 * Holds constants used for saving/loading into .fsm file format
 * @author kvasnict
 */
public interface Ifile {
    
    /**
     * delimiter in one record
     */
    public final static String DELIMITER = ";";

    
    
    /**
     * record type's index in record
     */
    public final static Integer IDX_TYPE = 0;

    /**
     * node center x-coordinate's index in record
     */
    public final static Integer IDX_N_X = 1;

    /**
     * node center y-coordinate's index in record
     */
    public final static Integer IDX_N_Y = 2;

    /**
     * node label's index in record
     */
    public final static Integer IDX_N_LABEL = 3;
    
    /**
     * node superscript's index in record
     */
    public final static Integer IDX_N_SUPERSCR = 4;

    /**
     * node subscript's index in record
     */
    public final static Integer IDX_N_SUBSCR = 5;
    
    /**
     * node subtype's index in record
     */
    public final static Integer IDX_N_SUBTYPE = 6;

    /**
     * transition from-node x-coordinate's index in record
     */
    public final static Integer IDX_T_FX = 1;

    /**
     * transition from-node y-coordinate's index in record
     */
    public final static Integer IDX_T_FY = 2;

    /**
     * transition to-node x-coordinate's index in record
     */
    public final static Integer IDX_T_TX = 3;

    /**
     * transition to-node y-coordinate's index in record
     */
    public final static Integer IDX_T_TY = 4;

    /**
     * transition label's index in record
     */
    public final static Integer IDX_T_LABEL = 5;

    /**
     * transition superscript's index in record
     */
    public final static Integer IDX_T_SUPERSCR = 6;

    /**
     * transition subscript's index in record
     */
    public final static Integer IDX_T_SUBSCR = 7;

    /**
     * transition subtype's index in record
     */
    public final static Integer IDX_T_SUBTYPE = 8;

    
    
    /**
     * record type: node
     */
    public final static Integer TYPE_NODE = 0;

    /**
     * record type: transition
     */
    public final static Integer TYPE_TRANS = 1;
    
    
    
    /**
     * normal node
     */
    public final static Integer SUB_N_NORMAL = 0;

    /**
     * start node
     */
    public final static Integer SUB_N_START = 1;

    /**
     * accept node
     */
    public final static Integer SUB_N_ACCEPT = 2;
    
    /**
     * start-accept node
     */
    public final static Integer SUB_N_STARTACCEPT = 3;

    /**
     * normal transition
     */
    public final static Integer SUB_T_NORMAL = 0;

    /**
     * epsilon transition
     */
    public final static Integer SUB_T_EPS = 1;
}