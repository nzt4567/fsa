package FSMBuilder.FSMmodel;

/**
 * Dummy interface holding every method that all objects in model should have.
 * @author Logan Fine and kvasnict
 * @version 2
 */
public interface IcommonElement {
    /**
     * detects if any part of the object is at these coordinates.
     * @param x X-coord
     * @param y Y-coord
     * @return true if yes
     */
    public boolean hasCoords(Integer x, Integer y);
    
    /**
     * every object is "visitable" by a drawing visitor implemented in the view
     * package. See wiki to understand how visitor pattern works.
     * @param v class handling drawing
     */
    public void acceptVisit(Ivisitor v);
    
    /**
     * label getter
     * @return Clabel
     */
    public Clabel getLabel();
    
    /**
     * label setter
     * @param l label
     * @return true if success
     */
    public boolean setLabel(Clabel l);
}
