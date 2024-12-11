package model;

import java.io.Serializable;
import java.util.Objects;

public class PlayerTeamId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int playerId;
    private String teamAbv;

    public PlayerTeamId() {}


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerTeamId that = (PlayerTeamId) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(teamAbv, that.teamAbv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, teamAbv);
    }
}
