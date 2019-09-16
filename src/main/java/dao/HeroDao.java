package dao;

import models.*;

import java.util.List;

public interface HeroDao{

    List<Hero> getAll();

    //create a hero
    void add(Hero hero);

    //read
    Hero findById(int id);

    //update hero
     void update(int id, String hero_name, int age, String hero_power, String hero_weakness, int squad_id);

    //delete
     void deleteById(int id);
     void clearAllHeroes();
}