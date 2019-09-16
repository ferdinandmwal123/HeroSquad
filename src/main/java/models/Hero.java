package models;

import java.util.ArrayList;
import java.util.Objects;

public class Hero{


    private int id;
    private String hero_name;
    private int hero_age;
    private String hero_power;
    private String hero_weakness;
    public int squad_id;
    private static ArrayList<Hero> instances = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id == hero.id &&
                hero_age == hero.hero_age &&
                squad_id == hero.squad_id &&
                Objects.equals(hero_name, hero.hero_name) &&
                Objects.equals(hero_power, hero.hero_power) &&
                Objects.equals(hero_weakness, hero.hero_weakness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hero_name, hero_age, hero_power, hero_weakness, squad_id);
    }

    public Hero(String hero_name, int age, String hero_power, String hero_weakness, int squad_id) {
        this.hero_name = hero_name;
        this.hero_age = age;
        this.hero_power = hero_power;
        this.hero_weakness = hero_weakness;
        this.squad_id = squad_id;
        instances.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHero_name() {
        return hero_name;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public int getAge() {
        return hero_age;
    }

    public void setAge(int age) {
        this.hero_age = age;
    }

    public String getHero_power() {
        return hero_power;
    }

    public void setHero_power(String hero_power) {
        this.hero_power = hero_power;
    }

    public String getHero_weakness() {
        return hero_weakness;
    }

    public void setHero_weakness(String hero_weakness) {
        this.hero_weakness = hero_weakness;
    }

    public int getSquad_id() {
        return squad_id;
    }

    public void setSquad_id(int squad_id) {
        this.squad_id = squad_id;
    }
}