package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    ModelTests.class,
    NodeTests.class,
})
public class ModelTestSuite
{ // no implementation needed; above annotations do the work.   
}