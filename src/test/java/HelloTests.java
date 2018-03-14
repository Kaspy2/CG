import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloTests {
    private Hello h;

    @Before
    public void setup() {
        h = new Hello();
    }

    @After
    public void teardown() {
        h = null;
    }

    @Test
    public void testSimpleGetMessage() {
        //Exercise
        String message = h.ay();

        //Verify
        assertEquals("Hey", message);
    }
}
