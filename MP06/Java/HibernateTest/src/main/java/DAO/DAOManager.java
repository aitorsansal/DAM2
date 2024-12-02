package DAO;

import model.Team;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOManager extends AutoCloseable {
    public boolean addTeam(Team oneTeam);
    public boolean deleteTeam(String teamAbbv);
    public Team updateTeam(Team oneTeam);
    public Team getTeamByAbbr(String abbr);
    public Team getTeamByName(String name);
    public ArrayList<Team> getAllTeams();
}
