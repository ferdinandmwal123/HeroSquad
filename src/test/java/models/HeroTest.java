package models;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeroTest{

    @Test
    public void heroInstantiatesHero(){
        Hero newHero = setUpHero();
        assertEquals(true, newHero instanceof Hero);
    }

    @Test
    public void heroInstantiatesWithName(){
        Hero newHero = setUpHero();
        assertEquals("jack",newHero.getHero_name());
    }
    @Test
    public void heroInstantiatesWithAge(){
        Hero newHero = setUpHero();
        assertEquals(4,newHero.getAge());
    }
    @Test
    public void heroInstantiatesWithPower(){
        Hero newHero = setUpHero();
        assertEquals("strong",newHero.getHero_power());
    }
    @Test
    public void heroInstantiatesWithWeakness(){
        Hero newHero = setUpHero();
        assertEquals("water",newHero.getHero_weakness());
    }
    @Test
    public void heroInstantiatesWithSquadId(){
        Hero newHero = setUpHero();
        assertEquals(4,newHero.getSquad_id());
    }

    //helper method
    public Hero setUpHero(){
        return new Hero("jack",4,"strong","water",4);
    }
    public Hero setUpHero2(){
        return new Hero("jill",6,"swim","fire",5);
    }
}