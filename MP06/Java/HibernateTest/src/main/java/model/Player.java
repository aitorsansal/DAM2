package model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "player")
@IdClass(PlayerTeamId.class)
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "team_abv")
    String teamAbv;
    @Id
    @Column(name = "player_id")
    Integer playerId;
    @Column(name = "name")
    String name;
    @Column(name = "height")
    Integer height;
    @Column(name = "position")
    String position;

    public Player(String teamAbv, Integer playerId, String name, Integer height, String position) {
        this.teamAbv = teamAbv;
        this.playerId = playerId;
        this.name = name;
        this.height = height;
        this.position = position;
    }

    public Player(String teamAbv, Integer playerId, String name, String position) {
        this.teamAbv = teamAbv;
        this.playerId = playerId;
        this.name = name;
        this.position = position;
    }

    public Player() {}


    public String getTeamAbv() {
        return teamAbv;
    }

    public void setTeamAbv(String teamAbv) {
        this.teamAbv = teamAbv;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
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

    @Override
    public String toString() {
        return name + ", " + teamAbv + ", " + playerId + ", " + height + ", " + position;
    }
}
