package FSMBuilder.FSMmodel;

/**
 *
 * @author kvasnict
 * Interface implemented by each class that wants to get notified about the 
 * changes in model - controller in our case.
 */

public interface ImodelListener {

    /**
     * Observer pattern - update method
     */
    public void update();   
}