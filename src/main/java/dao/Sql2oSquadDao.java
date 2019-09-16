package dao;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDao implements SquadDao{

    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Squad squad) {
        String sql = "INSERT INTO squads (squad_name, squad_size, squad_cause) VALUES (:squad_name, :squad_size, :squad_cause)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Squad> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads")
                    .executeAndFetch(Squad.class);
        }
    }

    @Override
    public Squad findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Squad.class);
        }
    }

    @Override
    public void update(int id, String new_squad_name, int new_squad_size, String new_squad_cause){
        String sql = "UPDATE squads SET (squad_name, squad_size, squad_cause) = (:squad_name, :squad_size, :squad_cause) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).addParameter("squad_name",new_squad_name)
                    .addParameter("squad_size",new_squad_size)
                    .addParameter("squad_cause",new_squad_cause)
                    .addParameter("id",id).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from squads WHERE id=:id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllSquads(){
        String sql ="DELETE from squads";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Hero> getAllHeroesBySquad(int squad_id){
        try (Connection con = sql2o.open()) {
            return  con.createQuery("SELECT * FROM heroes WHERE squad_id = :squad_id")
                    .addParameter("squad_id",squad_id)
                    .executeAndFetch(Hero.class);
        }
    }



}