import dao.HeroDao;
import dao.Sql2oHeroDao;
import dao.Sql2oSquadDao;
import models.Hero;
import models.Squad;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App{
    public static void main(String[] args) {
static int getHerokuAssignedPort(){
ProcessBuilder processBuilder = new ProcessBuilder();
if(processBuilder.environment().get("PORT") != null){

}
        }
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/herosquad.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oHeroDao heroDao = new Sql2oHeroDao(sql2o);
        Sql2oSquadDao squadDao = new Sql2oSquadDao(sql2o);

        //global model (DRY)


        //get show all tasks in all categories and show all categories
        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads",allSquads);
            List<Hero> heroes = heroDao.getAll();//change
            model.put("heroes",heroes);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new squad
        get("/squads/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads",squads);
            return new ModelAndView(model,"squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new squad
        post("/squads", (request, response) -> {
         Map<String, Object> model = new HashMap<>();
        String squad_name = request.queryParams("squad_name");
        int squad_size = Integer.parseInt(request.queryParams("squad_size"));
        String squad_cause = request.queryParams("squad_cause");
        Squad newSquad = new Squad(squad_name,squad_size,squad_cause);
        squadDao.add(newSquad);
        response.redirect("/");
         return null;
         }, new HandlebarsTemplateEngine());

        //get: delete allsquads and all heroes
        get("/squads/delete", (request, response) -> {
            squadDao.clearAllSquads();
            heroDao.clearAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get delete heroes
        get("/heroes/delete", (request, response) -> {
            heroDao.clearAllHeroes(); //later change to dao
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get a specific squad (and the heroes it contains)
        get("/squads/:id", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            //System.out.println(request.queryParams("id"));
            //Integer.parseInt(request.queryParams("id"))
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad",foundSquad);
            List<Hero> allHeroesBySquad = squadDao.getAllHeroesBySquad(idOfSquadToFind);
            model.put("heroes",allHeroesBySquad);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a squad
        get("/squads/:id/edit", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            model.put("editSquad",true);
            Squad squad = squadDao.findById(Integer.parseInt(request.params("id")));
            model.put("squad",squad);
            model.put("squads", squadDao.getAll());
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a squad
        post("/squads/:id/edit", (request, response) -> {
            int idOfSquadToEdit = Integer.parseInt(request.params("id"));
            String new_squad_name = request.queryParams("new_squad_name");
            int new_squad_size = Integer.parseInt(request.queryParams("new_squad_size"));
            String new_squad_cause = request.queryParams("new_squad_cause");
            squadDao.update(idOfSquadToEdit,new_squad_name,new_squad_size,new_squad_cause);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete a squad and heroes it contains


        //get: delete individual hero
        get("/heroes/:id/delete",(request, response) -> {
            int idOfHeroToDelete = Integer.parseInt(request.params("id"));
            heroDao.deleteById(idOfHeroToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());



        //get show new hero form
        get("/heroes/new",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> squads = squadDao.getAll();
            model.put("squads", squads);
            return new ModelAndView(model,"hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post process new hero form(above)
        post("/heroes",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads",allSquads);
            String hero_name = request.queryParams("hero_name");
            int hero_age = Integer.parseInt(request.queryParams("hero_age"));
            String hero_power = request.queryParams("hero_power");
            String hero_weakness = request.queryParams("hero_weakness");
            int squad_id = Integer.parseInt(request.queryParams("id"));
            Hero newHero = new Hero(hero_name,hero_age,hero_power,hero_weakness,squad_id);
            heroDao.add(newHero);
            response.redirect("/");
            return null;

        }, new HandlebarsTemplateEngine());

        //get show a hero nested in a squad
        get("/squads/:id/heroes/:id", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(request.params("id"));
            Hero foundHero = heroDao.findById(idOfHeroToFind);//change
            int idOfSquadToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = squadDao.findById(idOfSquadToFind);
            model.put("squad",foundSquad);
            model.put("hero",foundHero);
            model.put("squads",squadDao.getAll());
            return new ModelAndView(model,"hero-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get show form to update a hero
        get("/heroes/:id/edit", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Squad> allSquads = squadDao.getAll();
            model.put("squads",allSquads);
            Hero hero = heroDao.findById(Integer.parseInt(request.params("id")));
            model.put("hero",hero);
            model.put("editHero",true);
//            int idOfHeroToUpdate = Integer.parseInt(request.queryParams("id"));
//            Hero editHero = heroDao.findById(idOfHeroToUpdate);//change
//            model.put("editHero",editHero);
            return new ModelAndView(model,"hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post process form to update a hero
        post("/heroes/:id",(request, response) -> {
            int idOfHeroToEdit = Integer.parseInt(request.queryParams("id"));
            String new_hero_name = request.queryParams("hero_name");
            int new_hero_age = Integer.parseInt(request.queryParams("hero_age"));
            String new_hero_power = request.queryParams("hero_power");
            String new_hero_weakness = request.queryParams("hero_weakness");
            int new_squad_id = Integer.parseInt(request.queryParams("squad_id"));
            heroDao.update(idOfHeroToEdit,new_hero_name, new_hero_age, new_hero_power, new_hero_weakness,new_squad_id);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
    }

}