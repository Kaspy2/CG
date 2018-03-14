package cps2002.assignment.cg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTests {

    private HelloWorld helloWorld;

    @Before
    public void setup() {
        helloWorld = new HelloWorld();
    }

    @After
    public void teardown() {
        helloWorld = null;
    }

    @Test
    public void testSimpleGetMessage() {
        //Exercise
        String message = helloWorld.getMessage();

        //Verify
        assertEquals("Hello World", message);
    }

    @Test
    public void testAdd(){
        //Exercise
        int res = helloWorld.add(1,1);

        //Verify
        assertEquals(2,res);
    }
}
