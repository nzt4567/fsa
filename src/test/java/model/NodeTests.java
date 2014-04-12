package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.Ctrans;

@RunWith(JUnit4.class)
public class NodeTests
{
    private CnodeNormal m;
    private CnodeNormal n;
    private CnodeNormal o;
    private CnodeNormal p;

    private Ctrans t;
    private Ctrans u;

    @Before
	public void setUp()
    {
	m = new CnodeNormal(0,0);
    }

    @After
	public void tearDown()
    {
	m = null;
    }

    @Test
	public void constructor()
    {
	assertEquals("The node should be instantiated with coordinates 0,0",
		     0, (int)m.getCenter().m_x);
	assertEquals("The node should be instantiated with coordinates 0,0",
		     0, (int)m.getCenter().m_y);
    }

    @Test
	public void setCenter()
    {
	m.setCenter(5,3);

	assertEquals("The node's position should have changed to 5,3",
		     5, (int)m.getCenter().m_x);
	assertEquals("The node's position should have changed to 5,3",
		     3, (int)m.getCenter().m_y);
	m.setCenter(0,0);
	assertEquals("The node's position should be 0",
		     0, (int)m.getCenter().m_x);
	assertEquals("The node's positions should be 0",
		     0, (int)m.getCenter().m_y);
    }


}