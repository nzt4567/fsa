package FSMBuilder.FSMfunctions.fileIO;

import FSMBuilder.FSMfunctions.constants.Imisc;
import FSMBuilder.FSMview.View;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Export current screen to png/gif/jpeg
 * @author kvasnict
 */
public class CexportImage {

    /**
     * View
     */
    protected final View m_view;
    
    /**
     * Default constructor
     * @param v View
     */
    public CexportImage(View v) {
        if (v != null)
            m_view = v;
        else
            throw new RuntimeException("null in image exporter");
    }
    
    /**
     *
     * @param imagetype "GIF"/"JPEG"/"PNG"
     * @return true if success
     */
    public boolean export(String imagetype) {
        List<String> allowed_types = 
          new ArrayList<String>(Arrays.asList(ImageIO.getReaderFileSuffixes()));
        Container c = m_view.getFrame().getContentPane();
        BufferedImage i = new BufferedImage(c.getWidth(), c.getHeight(), 
                BufferedImage.TYPE_INT_ARGB);
        
        if (allowed_types.contains(imagetype.toLowerCase())) {
            c.paint(i.getGraphics());
            File f = m_view.filename(Imisc.SAVE_FILE);
            if (f != null) {
                try {
                    ImageIO.write(i, imagetype, f);
                    return true;
                } catch (IOException ex) {
                    throw new RuntimeException("exporting image failed");
                }
            }
        }
        
        return false;
    }
}