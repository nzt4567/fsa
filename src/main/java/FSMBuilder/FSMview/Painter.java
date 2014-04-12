package FSMBuilder.FSMview;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMmodel.Clabel;
import FSMBuilder.FSMmodel.Cpoint;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Duri Abdurahman Duri
 * Constructs a 2D Graphics Drawer which will give way for better drawings 
 * Lay out the font and location of the label, subscript and superscript
 */
public abstract class Painter {
    protected final Graphics2D m_graphics;
    
    /**
     * Default constructor
     * @param g graphics
     */
    public Painter (Graphics g) {
        if (g != null)
            m_graphics = (Graphics2D) g.create();
        else
            throw new RuntimeException("null in graphics");
    }
    
    /**
     * Common drawing operations for labels
     * @param l label
     * @param p center
     * @param offset variable offset
     */
    protected void drawLabelWithEverythig(Clabel l, Cpoint p, Integer offset) {
        if (l == null || p == null || offset == null)
            return;
        
        m_graphics.setFont(new Font(Imisc.FONT, Font.PLAIN, Imisc.FONT_SIZE));
        Integer label_w = m_graphics.getFontMetrics().stringWidth(l.getLabel());
        Integer label_h = m_graphics.getFontMetrics().getHeight();
        Integer label_x = p.m_x - label_w/2;
        Integer label_y = p.m_y - (offset + 10);
        Integer super_x = label_x + label_w + 2;
        Integer super_y = label_y - label_h/2 + 2;
        Integer sub_x = super_x;
        Integer sub_y = label_y + 5; // TODO: constants into Imisc
        
        m_graphics.drawString(l.getLabel(), label_x, label_y);
        m_graphics.setFont(new Font(Imisc.FONT, Font.PLAIN, label_h/2));
        m_graphics.drawString(l.getSuperscript(), super_x , super_y);
        m_graphics.drawString(l.getSubscript(), sub_x , sub_y);
    }
    
    /**
     * Draw the label
     * @param l label
     */
    public abstract void drawLabel(Clabel l);
}