package models;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquadTest {
    @Test
    public void squadInstantiatesCorrectly() throws Exception{
      Squad squad = setUpSquad();
      assertTrue(squad instanceof Squad);
    }

    @Test
    public void squadInstantiatesWithName() throws Exception{
        Squad squad = setUpSquad();
        assertEquals("Kool", squad.getSquad_name());
    }

    @Test
    public void squadInstantiatesWithSize() throws  Exception{
        Squad squad = setUpSquad();
        assertEquals(5, squad.getSquad_size());
    }

    @Test
    public void squadInstantiatesWIthCause() throws Exception{
        Squad squad = setUpSquad();
        assertEquals("love", squad.getSquad_cause());
    }

public Squad setUpSquad(){
        return new Squad("Kool",5,"love");
}
}