package FSMBuilder.FSMfunctions.fileIO.dotTEX;

import FSMBuilder.FSMfunctions.fileIO.dotFSM.Csaver;
import FSMBuilder.FSMmodel.CnodeAccept;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.CnodeStart;
import FSMBuilder.FSMmodel.CnodeStartAccept;
import FSMBuilder.FSMmodel.CtransEps;
import FSMBuilder.FSMmodel.CtransNormal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class will save in a latex readable format.
 * @author Logan Fine
 */

public class CsaverLatex extends Csaver
{

    /**
     * Default constructor
     * @param F File to save to
     */
    public CsaverLatex(File F)
    {
        super(F);
	m_toWrite = "";
	prepareWrite();

	if (F != null)
	    {
		m_file = F;
	    }
	else
	    {
		throw new RuntimeException("The file you are trying to save is null");
	    }
    }

    /**
     * Write string to file
     * @return true if success
     */
    @Override
    public boolean save() 
    {
        if (! m_toWrite.equals("") && m_file != null)
            try {
		finishWrite();
                FileWriter w = new FileWriter(m_file, true);
                w.write(m_toWrite);
                w.close();
                return true;
            } catch (IOException ioe) {
                System.err.println("Writing .tex failed: " + ioe.getMessage());
            }
        
        return false;
    }


    private void prepareWrite()
    {
	m_toWrite += "\\documentclass[12pt]{article}" + "\n" +
                     "\\usepackage{tikz}" + "\n" + "\n" +
	             "\\begin{document}" + "\n" + "\n" +
	             "\\begin{center}" + "\n" +
	             "\\begin{tikzpicture}[scale=0.2]" + "\n" +
	             "\\tikzstyle{every node}+=[inner sep=0pt]" + "\n";
    }

    private void finishWrite()
    {
	m_toWrite += "\\end{tikzpicture}" + "\n" +
	             "\\end{center}" + "\n" + "\n" +
	             "\\end{document}";
    }
	    
    @Override
    public void visit(CnodeAccept s) {
	//draw node
        m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (3);" + "\n";
	m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (2.4);" + "\n";
	//draw labels
	m_toWrite += "\\draw (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") node {$" + s.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y + 1) + ") node {$" + s.getLabel().getSuperscript() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y - 1) + ") node {$" + s.getLabel().getSubscript() + "$};" + "\n";
    }

    @Override
    public void visit(CnodeNormal s) {
	//draw node
        m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (3);" + "\n";
	//draw labels
	m_toWrite += "\\draw (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") node {$" + s.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y + 1) + ") node {$" + s.getLabel().getSuperscript() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y - 1) + ") node {$" + s.getLabel().getSubscript() + "$};" + "\n";
    }

    @Override
    public void visit(CnodeStart s) {
	//draw node
        m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (3);" + "\n";
	
	//draw start arrow
	m_toWrite += "\\fill [black] (" + (s.getCenter().m_x - 3) + s.getCenter().m_y + ") -- (" +
	    (s.getCenter().m_x-3-2) + (s.getCenter().m_y-2) + ") -- (" + (s.getCenter().m_x-3-2) + (s.getCenter().m_y+2) + ");" + "\n";

	//draw labels
	m_toWrite += "\\draw (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") node {$" + s.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y + 1) + ") node {$" + s.getLabel().getSuperscript() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y - 1) + ") node {$" + s.getLabel().getSubscript() + "$};" + "\n";
    }

    @Override
    public void visit(CnodeStartAccept s) {
	//draw node
        m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (3);" + "\n";
	m_toWrite += "\\draw [black] (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") circle (2.4);" + "\n";
	//draw start arrow
	m_toWrite += "\\fill [black] (" + (s.getCenter().m_x - 3) + s.getCenter().m_y + ") -- (" +
	    (s.getCenter().m_x-3-2) + (s.getCenter().m_y-2) + ") -- (" + (s.getCenter().m_x-3-2) + (s.getCenter().m_y+2) + ");" + "\n";
	//draw labels
	m_toWrite += "\\draw (" + s.getCenter().m_x + "," + s.getCenter().m_y + ") node {$" + s.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y + 1) + ") node {$" + s.getLabel().getSuperscript() + "$};" + "\n";
	m_toWrite += "\\draw (" + (s.getCenter().m_x + 1) + "," + (s.getCenter().m_y - 1) + ") node {$" + s.getLabel().getSubscript() + "$};" + "\n";
    }

    @Override
    public void visit(CtransNormal t) {
        m_toWrite += "\\draw [black] (" + t.getFrom().getCenter().m_x + "," + t.getFrom().getCenter().m_y + ") -- (" + t.getTo().getCenter().m_x + ", " + t.getTo().getCenter().m_y + ");" + "\n";

	//draw labels
	m_toWrite += "\\draw (" + ((t.getFrom().getCenter().m_x + t.getTo().getCenter().m_x))/2 + "," + ((t.getFrom().getCenter().m_y + t.getTo().getCenter().m_y))/2 + ") node " +
	             "{$" + t.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + ((t.getFrom().getCenter().m_x + 
                t.getTo().getCenter().m_x)/2 + 1) + "," + 
                (((t.getFrom().getCenter().m_y + 
                t.getTo().getCenter().m_y))/2 + 1) + ") node {$" +
                t.getLabel().getSuperscript() + "$};" + "\n";
	m_toWrite += "\\draw (" + ((t.getFrom().getCenter().m_x + t.getTo().getCenter().m_x)/2 + 1) + "," + (((t.getFrom().getCenter().m_y + t.getTo().getCenter().m_y))/2 - 1) + ") node " + "{$" + t.getLabel().getSubscript() + "$};" + "\n";
	
	
    }

    @Override
    public void visit(CtransEps t) {
	m_toWrite += "\\draw [black] (" + t.getFrom().getCenter().m_x + "," + t.getFrom().getCenter().m_y + ") -- (" + 
                t.getTo().getCenter().m_x + "," + t.getTo().getCenter().m_y + ");" + "\n";

	//draw labels
	m_toWrite += "\\draw (" + ((t.getFrom().getCenter().m_x + t.getTo().getCenter().m_x))/2 + "," + ((t.getFrom().getCenter().m_y + t.getTo().getCenter().m_y))/2 + ") node " + "{$" + t.getLabel().getLabel() + "$};" + "\n";
	m_toWrite += "\\draw (" + ((t.getFrom().getCenter().m_x + t.getTo().getCenter().m_x)/2 + 1) + "," + (((t.getFrom().getCenter().m_y + t.getTo().getCenter().m_y))/2 - 1) + ") node " + "{$" + t.getLabel().getSubscript() + "$};"+ "\n";
    }
}