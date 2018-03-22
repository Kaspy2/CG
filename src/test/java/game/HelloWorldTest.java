package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

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
	public void testGetOne(){
		assertEquals(1,helloWorld.getOne());
	}

}