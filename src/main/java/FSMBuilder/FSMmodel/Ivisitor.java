package FSMBuilder.FSMmodel;

/**
 * Interface for every class implementing the drawing visitor
 * @author kvasnict
 */
public interface Ivisitor {

    /**
     * Visitor pattern - visit
     * @param s CnodeAccept to visit
     */
    public void visit(CnodeAccept s);

    /**
     * Visitor pattern - visit
     * @param s CnodeNormal to visit
     */
    public void visit(CnodeNormal s);

    /**
     * Visitor pattern - visit
     * @param s CnodeStart to visit
     */
    public void visit(CnodeStart s);

    /**
     * Visitor pattern - visit
     * @param s CnodeStartAccept to visit
     */
    public void visit(CnodeStartAccept s);
    
    /**
     * Visitor pattern - visit
     * @param t CtransNormal to visit
     */
    public void visit(CtransNormal t);
    
    /**
     * Visitor pattern - visit
     * @param t CtransEps to visit
     */
    public void visit(CtransEps t);   
}