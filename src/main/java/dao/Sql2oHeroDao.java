package dao;

import models.Hero;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oHeroDao implements HeroDao{


    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Hero hero){
        String sql ="INSERT INTO heroes (hero_name, hero_power, hero_weakness, squad_id) VALUES (:hero_name, :hero_power, :hero_weakness, :squad_id)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(hero)
                    .executeUpdate()
                    .getKey();
            hero.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Hero> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM heroes")
                    .executeAndFetch(Hero.class);
        }
    }

    @Override
    public Hero findById(int id){
        try(Connection con = sql2o.open()){
            return  con.createQuery("SELECT * FROM heroes WHERE id = :id").addParameter("id",id)
                    .executeAndFetchFirst(Hero.class);
        }
    }

    @Override
    public void update(int id, String new_hero_name, int new_hero_age, String new_hero_power, String new_hero_weakness, int new_squad_id){
        String sql = "UPDATE heroes SET (hero_name, hero_age, hero_power, hero_weakness, squad_id) = (:hero_name, :hero_age, :hero_power, :hero_weakness, :squad_id) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("hero_name", new_hero_name).addParameter("hero_age",new_hero_age)
                    .addParameter("hero_power",new_hero_power).addParameter("hero_weakness",new_hero_weakness)
                    .addParameter("squad_id",new_squad_id).addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id){
        String sql = "DELETE from heroes WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllHeroes(){
        String sql = "DELETE from heroes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}