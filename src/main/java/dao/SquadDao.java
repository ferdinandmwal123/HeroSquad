package dao;
import models.*;


import java.util.List;

public interface SquadDao{

    //ListOfSquads
    List<Squad> getAll();

    //CREATE SQUADS
    void add (Squad squad);

    //Read squads
    Squad findById(int id);
    //getAllHeroesBySquad
    List<Hero> getAllHeroesBySquad(int squad_id);

    //update a squad
    void update (int id, String new_name, int new_size, String new_cause);

    //delete a squad
    void deleteById(int id);
    void clearAllSquads();

}