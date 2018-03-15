package First;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator c;

    @Before
    public void setup(){
        c = new StringCalculator();
    }

    @After
    public void teardown(){
        c = null;
    }

    @Test
    public void StrAddTest(){
        int ans = c.add("");
        assertEquals(0,ans);
    }


}
