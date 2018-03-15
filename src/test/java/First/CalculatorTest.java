package First;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CalculatorTest {
    private Calculator calc;

    @Before
    public void setup(){
        calc = new Calculator();
    }

    @After
    public void teardown(){
        calc = null;
    }

    @Test
    public void AddTest(){
        int ans = calc.add(0,1);

        assertEquals(1,ans);
    }

    @Test
    public void SubTest(){
        int ans = calc.subtract(1,1);

        assertEquals(0,ans);
    }

    @Test
    public void MulTest(){
        int ans = calc.multiply(0,1);

        assertEquals(0,ans);
    }

    @Test
    public void DivTest(){
        int ans = calc.divide(0,1);

        assertEquals(0,ans);
    }

    @Test
    public void DivTest0(){
        int ans = calc.divide(1,0);

        assertEquals(-999,ans);
    }
}
