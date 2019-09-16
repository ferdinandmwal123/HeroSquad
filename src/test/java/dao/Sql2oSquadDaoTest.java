package dao;


import models.Hero;
import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Sql2oSquadDaoTest {

    private Sql2oSquadDao squadDao;
    private Sql2oHeroDao heroDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly() throws Exception {
        Squad squad = setUpSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero newHero = new Hero("jack", 4, "strong", "water", squadId);
        Hero otherHero = new Hero("jill", 12, "swim", "fire", squadId);
        Hero thirdHero = new Hero("john", 13, "fly", "air", squadId);
        heroDao.add(newHero);
        heroDao.add(otherHero);
        assertEquals(2, squadDao.getAllHeroesBySquad(squadId));
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHero));
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(otherHero));
        assertFalse(squadDao.getAllHeroesBySquad(squadId).contains(thirdHero)); //things are accurate!
    }


    public Squad setUpSquad() {
        return new Squad("fools", 5, "love");
    }

}


