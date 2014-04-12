package model;

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
import FSMBuilder.FSMmodel.Cnode;
import FSMBuilder.FSMmodel.CnodeNormal;
import FSMBuilder.FSMmodel.Ctrans;
import FSMBuilder.FSMmodel.CtransNormal;


@RunWith(JUnit4.class)
public class ModelTests
{
    private Cmodel m;
    private CnodeNormal n;
    private CnodeNormal o;
    private CnodeNormal p;

    private CtransNormal t;
    private CtransNormal u;

    @Before
	public void setUp()
    {
	m = new Cmodel();

	n = new CnodeNormal(0,0);
	o = new CnodeNormal(1,1);
	p = new CnodeNormal(2,2);
	
	t = new CtransNormal(n,o);
	u = new CtransNormal(o,p);
    }

    @After
	public void tearDown()
    {
	m = null;

	n = null;
	o = null;
	p = null;
	
	t = null;
	u = null;	
    }

    private void fillModel()
    {
	m.addNode(n);
	m.addNode(o);
	m.addNode(p);

        CtransNormal t1 = new CtransNormal(n, o);
        CtransNormal t2 = new CtransNormal(o, p);
	m.addTransition(t1);
	m.addTransition(t2);
    }

    private Set<Cnode> testNodeList()
    {
	Set<Cnode> returnList = new HashSet<Cnode>();
	returnList.add(n);
	returnList.add(o);
	returnList.add(p);

	return returnList;
    }

    private Set<Ctrans> testTransitionList()
    {
	Set<Ctrans> returnList = new HashSet<Ctrans>();
	returnList.add(t);
	returnList.add(u);

	return returnList;
    }
	

    @Test
	public void addNode()
    {
	assertTrue("The model should return true for a successful addition",
		   m.addNode(n));
	assertTrue("The model should now have the node n",
		   m.hasNode(n));
	assertFalse("The model should return false for a failed addition",
		    m.addNode(n));
    }

    @Test
	public void addTransition()
    {	
	assertFalse("The model should not return false for adding a transition to nodes that don't exist yet",
		    m.addTransition(new CtransNormal(o, o)));
	assertFalse("The model should not have the transition",
		    m.hasTransition(t));

	m.addNode(n);
	m.addNode(o);
	assertTrue("The model should return true for a successful addition",
		   m.addTransition(new CtransNormal(n, o)));
	assertTrue("The model should now have the transition",
		   m.hasTransition(t));

	assertFalse("The model should not have another transition stemming from o",
		    m.hasTransition(u));

	CtransNormal s1 = new CtransNormal(n, n);
        CtransNormal s2 = new CtransNormal(n, n);
	assertTrue("The model should successfully add a self transition",
		   m.addTransition(s1));
	assertTrue("The model should now have that transition",
		   m.hasTransition(s2));
    }

    @Test
	public void hasNode()
    {
	assertFalse("There are no nodes in the model yet",
		    m.hasNode(o));
	
	m.addNode(o);
	assertTrue("The model should now have node o",
		   m.hasNode(o));
    }

    @Test
	public void hasTransition()
    {
	assertFalse("There are no transitions in the model yet",
		    m.hasTransition(t));

	m.addNode(o);
	m.addNode(p);
	m.addNode(n);
	Ctrans temp = new CtransNormal(o,p); 
	m.addTransition(temp);
	m.addTransition(temp);
	
	assertFalse("There should not be transitions in the model now",
		   m.hasTransition(t));
	assertTrue("There should be transitions in the model now",
		   m.hasTransition(u));
    }

    @Test
	public void getNode()
    {
	assertEquals("There are no nodes in the model to get",
		     null, m.getNode(0,0));
	m.addNode(n);
	assertEquals("The model should now retrieve node n",
		     n, m.getNode(0,0));	    
    }

    @Test
	public void getTransition()
    {
	assertEquals("There are no transitions in the model to get",
		     null, m.getTransition(0,0));

	CnodeNormal x = new CnodeNormal(10,10);
	m.addNode(n);
	m.addNode(x);
	
	CtransNormal y = new CtransNormal(n,x);
	Ctrans tempp = new CtransNormal(n,x);
        m.addTransition(tempp);

	assertEquals("The model should return transition y",
		     y, m.getTransition(5,5));
	
    }

    @Test
	public void delNode()
    {
	fillModel();

	m.delNode(n);
	assertFalse("The model should no longer have node n",
		    m.hasNode(n));
	assertFalse("The model should no longer have transition t",
		    m.hasTransition(t));
	
	assertTrue("The model should still have other nodes",
		   m.hasNode(o));
	assertTrue("The model should still have other nodes",
		   m.hasNode(p));
	assertTrue("The model should still have the other transition",
		   m.hasTransition(u));
	
    }

    @Test
	public void delTransition()
    {
	fillModel();
	
	m.delTransition(t);
	assertFalse("The model should no longer have transition t",
		    m.hasTransition(t));
	assertTrue("The model should still have all nodes",
		   m.hasNode(n));
	assertTrue("The model should still have all nodes",
		   m.hasNode(o));
	assertTrue("The model should still have all nodes",
		   m.hasNode(p));
	assertTrue("The model should still have the other transition",
		   m.hasTransition(u));
    }

    @Test
	public void getNodes()
    {
	Set<Cnode> emptyList = new HashSet<Cnode>();
	assertEquals("The list of nodes should be empty",
		     emptyList, m.getNodes());

	fillModel();
	Set<Cnode> testList = testNodeList();
	assertEquals("The list of nodes should now be full",
		     testList, m.getNodes());
    }

    @Test
	public void getTransitions()
    {
	Set<Cnode> emptyList = new HashSet<Cnode>();
	assertEquals("The list of transitions should be empty",
		     emptyList, m.getTransitions());
	
	fillModel();
	Set<Ctrans> testList = testTransitionList();
	assertEquals("The list of transitions should now be full",
		     testList, m.getTransitions());
    }
    
    @Test
	public void equals()
    {
	assertFalse("This model is not null",
		    m.equals(null));
	assertTrue("This model is equal to itself",
		   m.equals(m));
	assertFalse("This model is not equal to other objects",
		    m.equals(new ArrayList()));

	fillModel();

	Cmodel other = new Cmodel();
	other.addNode(n);
	
	assertFalse("This model is not equal to other",
		    m.equals(other));

	other.addNode(o);
	other.addNode(p);
	assertFalse("This model is not equal to other, which has no transitions",
		    m.equals(other));
	Ctrans tmp = new CtransNormal(n,o);
	other.addTransition(tmp);
	assertFalse("The models are still not equal",
		    m.equals(other));
        tmp = new CtransNormal(o, p);
	other.addTransition(tmp);
	assertTrue("The models are now equal",
		   m.equals(other));
    }
}