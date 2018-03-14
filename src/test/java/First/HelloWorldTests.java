package First;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTests {

    HelloWorld hw;

    @Before
    public void setup(){
        hw = new HelloWorld();
    }

    @After
    public void teardown(){
        hw = null;
    }

    @Test
    public void testSimpleGetMessage(){
        //exercise
        String msg = hw.getMessage();

        //verify
        assertEquals("Hello World!!", msg);
    }



    @Test
    public void testSimpleGetMessageName(){
        //exercise
        String msg = hw.getMessage("mike");

        //verify
        assertEquals("Hello mike", msg);
    }

    @Test
    public void testSimpleGetMessageNameNull(){
        //exercise
        String msg = hw.getMessage(null);

        //verify
        assertEquals("Hello World!!", msg);
    }

    @Test
    public void testSimpleGetMessageNameWilliam(){
        //exercise
        String msg = hw.getMessage("William");

        //verify
        assertEquals("Aw kink!", msg);
    }

    @Test
    public void testGetMsgNZero(){
        //exercise
        String msg = hw.getMessage(0);

        //verify
        assertEquals("", msg);
    }

    @Test
    public void testGetMsgNLTZero(){
        //exercise
        String msg = hw.getMessage(-1);

        //verify
        assertEquals("", msg);
    }

    @Test
    public void testGetMsgNOne(){
        //exercise
        String msg = hw.getMessage(1);

        //verify
        assertEquals("Hello World!!", msg);
    }

    @Test
    public void testGetMsgNFive(){
        //exercise
        String msg = hw.getMessage(5);

        //verify
        assertEquals("Hello World!!Hello World!!Hello World!!Hello World!!Hello World!!", msg);
    }
}
