package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapCreatorTest {
    private MapCreator map_creator;
    private int size;

    @Before
    public void setup(){
        size = 25;
		map_creator = new MapCreator('s', size);
	}

    @After
	public void teardown(){
		map_creator = null;
	}

    @Test
    public void testCorrectTypeSafe(){
        Map tmp_map = new SafeMap(size);
        assertTrue(tmp_map.getClass() == map_creator.getMap().getClass());
    }

    @Test
    public void testCorrectTypeHazard(){
        map_creator = new MapCreator('h', size);
        Map tmp_map = new HazardousMap(size);
        assertTrue(tmp_map.getClass() == map_creator.getMap().getClass());
    }

    @Test
    public void testIncorrectType(){
        map_creator = new MapCreator('f', size);
        Map tmp_map = null;
        assertTrue(tmp_map == map_creator.getMap());
    }

}
