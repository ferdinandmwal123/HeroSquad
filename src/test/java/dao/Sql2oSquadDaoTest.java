package dao;


import models.Hero;
import models.Squad;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
public void addingSquadSetsId() throws  Exception{
        Squad squad = setUpSquad();
        int firstId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(firstId,squad.getId());
}

    @Test
    public void getAllReturnsAllSquads() throws  Exception{
       Squad squad = setUpSquad();
       squadDao.add(squad);
       assertEquals(1,squadDao.getAll().size());
    }

    @Test
    public void existingSquadsCanBeFoundById() throws  Exception {
        Squad squad = setUpSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad,foundSquad);
    }
    @Test
    public void updateChangesSquadDetails(){
        Squad squad = setUpSquad();
        squadDao.add(squad);
        squadDao.update(squad.getId(),"Jill",8,"Fun");
        Squad newSquad = squadDao.findById(squad.getId());
        assertNotEquals(squad,newSquad);
    }

    @Test
    public void deletByIdDeletesRightSquad() throws  Exception{
        Squad squad = setUpSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        assertEquals(0,squadDao.getAll().size());
    }

    @Test
    public void clearAllSquadsRemovesAllSquads() throws Exception{
        Squad squad = setUpSquad();
        squadDao.add(squad);
        squadDao.clearAllSquads();
        assertEquals(0,squadDao.getAll().size());
    }

    @Test
    public void getAllHeroesBySquadReturnsHeroes() throws Exception{
        Squad squad = setUpSquad();
        squadDao.add(squad);
        Hero hero = new Hero("squid",2,"strong","sew",squad.getId());
        Hero newHero = new Hero("were",4,"fly","gte",squad.getId());
        heroDao.add(hero);
        heroDao.add(newHero);
        ArrayList<Object> heroes= new ArrayList<>();
        heroes.add(hero);
        heroes.add(newHero);
        assertEquals(2,squadDao.getAllHeroesBySquad(squad.getId()).size());
//        assertTrue(squadDao.getAllHeroesBySquad(squad.getId()).contains(hero));
//        assertTrue(squadDao.getAllHeroesBySquad(squad.getId()).contains(newHero));
        assertEquals(heroes ,squadDao.getAllHeroesBySquad(squad.getId()));
//        assertEquals(newHero,squadDao.getAllHeroesBySquad(squad.getId()));
    }


    public Squad setUpSquad() {
        return new Squad("fools", 5, "love");
    }

}


