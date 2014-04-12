package FSMBuilder.FSMmodel;

/**
 *
 * @author kvasnict
 */
public class CnodeStartAccept extends Cnode {

    public CnodeStartAccept(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public void acceptVisit(Ivisitor v) {
        v.visit(this);
    }
}
