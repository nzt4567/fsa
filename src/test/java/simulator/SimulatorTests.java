package simulator;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import FSMBuilder.FSMmodel.Cmodel;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.CtransNormal;
import FSMBuilder.FSMmodel.CnodeAccept;
import FSMBuilder.FSMmodel.CnodeStart;
import FSMBuilder.FSMmodel.Clabel;

import FSMBuilder.FSMfunctions.simulator.Csimulator;


@RunWith(JUnit4.class)
public class SimulatorTests
{
    private Cmodel m;

    private Csimulator sim;

    private CnodeStart start;
    private CnodeAccept end;

    private CnodeNormal a;
    private CnodeNormal b;
    private CnodeNormal c;
    private CnodeNormal d;



    
    @Before
	public void setUp()
    {
	m = new Cmodel();

	sim = new Csimulator(m);

	start = new CnodeStart(1,1);
	end = new CnodeAccept(2,2);

	a = new CnodeNormal(3,3);
	b = new CnodeNormal(4,4);
	c = new CnodeNormal(5,5);
	d = new CnodeNormal(6,6);
    }

    @After
	public void tearDown()
    {
	m = null;
	
	sim = null;
	
	start = null;
	end = null;
	
	a= null;
	b = null;
	c = null;
	d = null;

    }

    private void fillModel1()
    {
	m.addNode(start);
	
	m.addNode(a);
	m.addNode(b);
	
	m.addNode(end);

    }

    

    @Test
	public void straightAccept()
    {
	fillModel1();
	sim.setInput("qrs");

	CtransNormal h = new CtransNormal(start, a);
	CtransNormal i = new CtransNormal(a,b);
	CtransNormal j = new CtransNormal(b,end);

	m.addTransition(h);
	m.addTransition(i);
	m.addTransition(j);

	m.makeLabel(h, new Clabel("q","",""));
	m.makeLabel(i, new Clabel("r","",""));
	m.makeLabel(j, new Clabel("s","",""));

	sim.reset();
	boolean stop = false;

	while (!stop)
	    {
		if (sim.next() == false)
		    {
			stop = true;
		    }
	    }
	sim.next();
	assertTrue("The string should have been accepted", sim.accepted());
    }

    
    @Test
	public void notAccepted()
    {
	fillModel1();
	sim.setInput("qrt");

	CtransNormal h = new CtransNormal(start, a);
	CtransNormal i = new CtransNormal(a,b);
	CtransNormal j = new CtransNormal(b,end);

	m.addTransition(h);
	m.addTransition(i);
	m.addTransition(j);

	m.makeLabel(h, new Clabel("q","",""));
	m.makeLabel(i, new Clabel("r","",""));
	m.makeLabel(j, new Clabel("s","",""));

	sim.reset();
	boolean stop = false;

	while (!stop)
	    {
		if (sim.next() == false)
		    {
			stop = true;
		    }
	    }
	sim.next();
	assertFalse("The string should not have been accepted", sim.accepted());
    }

    @Test
	public void tooLong()
    {
	fillModel1();
	sim.setInput("qrst");

	CtransNormal h = new CtransNormal(start, a);
	CtransNormal i = new CtransNormal(a,b);
	CtransNormal j = new CtransNormal(b,end);

	m.addTransition(h);
	m.addTransition(i);
	m.addTransition(j);

	m.makeLabel(h, new Clabel("q","",""));
	m.makeLabel(i, new Clabel("r","",""));
	m.makeLabel(j, new Clabel("s","",""));

	sim.reset();
	boolean stop = false;

	while (!stop)
	    {
		if (sim.next() == false)
		    {
			stop = true;
		    }
	    }
	sim.next();
	assertFalse("The string should not have been accepted, as it is too long", sim.accepted());
    }

    @Test
	public void basicEpsilon()
    {
	fillModel1();
	sim.setInput("qs");

	CtransNormal h = new CtransNormal(start, a);
	CtransNormal i = new CtransNormal(a,b);
	CtransNormal j = new CtransNormal(b,end);

	m.addTransition(h);
	m.addTransition(i);
	m.addTransition(j);

	m.makeLabel(h, new Clabel("q","",""));
	m.makeLabel(i, new Clabel("*","",""));
	m.makeLabel(j, new Clabel("s","",""));

	sim.reset();
	boolean stop = false;

	while (!stop)
	    {
		if (sim.next() == false)
		    {
			stop = true;
		    }
	    }
	sim.next();
	assertTrue("The string should be accepted, as the epsilon carries it through", sim.accepted());
    }

    @Test
	public void epsilonFail()
    {
	fillModel1();
	sim.setInput("qr");

	CtransNormal h = new CtransNormal(start, a);
	CtransNormal i = new CtransNormal(a,b);
	CtransNormal j = new CtransNormal(b,end);

	m.addTransition(h);
	m.addTransition(i);
	m.addTransition(j);

	m.makeLabel(h, new Clabel("q","",""));
	m.makeLabel(i, new Clabel("*","",""));
	m.makeLabel(j, new Clabel("s","",""));

	sim.reset();
	boolean stop = false;

	while (!stop)
	    {
		if (sim.next() == false)
		    {
			stop = true;
		    }
	    }
	sim.next();
	assertFalse("The string should not have been accepted, as the last letters don't match", sim.accepted());
    }

}