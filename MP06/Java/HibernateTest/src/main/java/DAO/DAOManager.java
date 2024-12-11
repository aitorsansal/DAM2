package DAO;

import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public interface DAOManager extends AutoCloseable {
    public boolean addTeam(Team oneTeam);
    public boolean deleteTeam(String teamAbbv);
    public Team updateTeam(Team oneTeam);
    public Team getTeamByAbbr(String abbr);
    public Team getTeamByName(String name);
    public ArrayList<Team> getAllTeams();
    public boolean addPlayer(Player onePlayer);
    public int importPlayers(String playersFileName);
    public List<Player> getTeamPlayers(String teamAbv);
    public boolean addTeam(Team oneTeam, List<Player> plantilla);
    public void clearDBForTesting();
}
