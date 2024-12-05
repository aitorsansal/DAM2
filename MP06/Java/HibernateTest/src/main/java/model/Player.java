package model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "player")
@IdClass(PlayerTeamId.class)
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "team_abv")
    String team_abv;
    @Id
    @Column(name = "player_id")
    Integer player_id;
    @Column(name = "name")
    String name;
    @Column(name = "height")
    Integer height;
    @Column(name = "position")
    String position;

    public Player(String team_abv, Integer player_id, String name, Integer height, String position) {
        this.team_abv = team_abv;
        this.player_id = player_id;
        this.name = name;
        this.height = height;
        this.position = position;
    }

    public Player(String team_abv, Integer player_id, String name, String position) {
        this.team_abv = team_abv;
        this.player_id = player_id;
        this.name = name;
        this.position = position;
    }

    public Player() {}


    public String getTeam_abv() {
        return team_abv;
    }

    public void setTeam_abv(String team_abv) {
        this.team_abv = team_abv;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
