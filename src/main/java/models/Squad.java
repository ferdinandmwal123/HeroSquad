package models;

import java.util.Objects;

public class Squad{

    private int id;
    private String squad_name;
    private int squad_size;
    private String squad_cause;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squad squad = (Squad) o;
        return id == squad.id &&
                squad_size == squad.squad_size &&
                Objects.equals(squad_name, squad.squad_name) &&
                Objects.equals(squad_cause, squad.squad_cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, squad_name, squad_size, squad_cause);
    }

    public Squad(String squad_name, int squad_size, String squad_cause) {
        this.squad_name = squad_name;
        this.squad_size = squad_size;
        this.squad_cause = squad_cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSquad_name() {
        return squad_name;
    }

    public void setSquad_name(String squad_name) {
        this.squad_name = squad_name;
    }

    public int getSquad_size() {
        return squad_size;
    }

    public void setSquad_size(int squad_size) {
        this.squad_size = squad_size;
    }

    public String getSquad_cause() {
        return squad_cause;
    }

    public void setSquad_cause(String squad_cause) {
        this.squad_cause = squad_cause;
    }
}