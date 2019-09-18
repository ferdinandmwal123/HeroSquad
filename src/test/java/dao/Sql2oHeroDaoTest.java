package dao;


import models.Hero;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }



    @Test
    public void addingHeroSetsId() throws Exception {
        Hero hero = setUpHero();
        int originalHeroId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalHeroId,hero.getId());
    }



    @Test
    public void squadIdIsReturned() throws Exception {
        Hero hero = setUpHero();
        int originalSqId = hero.getSquad_id();
        heroDao.add(hero);
        assertEquals(originalSqId,heroDao.findById(hero.getId()).getSquad_id());
    }

    @Test
    public void updateChangesHeroesDetails() throws Exception {
        String initialName = "jack";
        int initialAge = 4;
        String initialPower = "strong";
        String initialWeakness = "water";
        int initialSquadId = 4;
        Hero hero = new Hero(initialName,initialAge,initialPower,initialWeakness,initialSquadId );
        heroDao.add(hero);
        heroDao.update(hero.getId(),"jill",5,"fly","air",8);
        Hero updatedHero = heroDao.findById(hero.getId());
        assertNotEquals(initialName,updatedHero.getHero_name());
        assertNotEquals(initialAge,updatedHero.getAge());
        assertNotEquals(initialPower,updatedHero.getHero_power());
        assertNotEquals(initialWeakness,updatedHero.getHero_weakness());
        assertNotEquals(initialSquadId,updatedHero.getSquad_id());
    }


    //helper method
    public Hero setUpHero(){
        return new Hero("jack",4,"strong","water",4);
    }
    public Hero setUpHero2(){
        return new Hero("jill",6,"swim","fire",5);
    }
}


