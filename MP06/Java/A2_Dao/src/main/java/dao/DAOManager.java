package dao;

import java.util.ArrayList;
import java.util.Date;

import model.Match;
import model.Team;

public interface DAOManager extends AutoCloseable {
	public boolean AddTeam(Team oneTeam) throws DAOException;
	public Team GetTeam(String teamAbreviation) throws DAOException;
	public String GetTeamAbbreviation(String teamName) throws DAOException;
	public Match GetMatch(Date matchDay, Team homeTeam, Team awayTeam) throws DAOException;
	public int GoalsOfTeam(Team t) throws DAOException;
	public int AwayGoals() throws DAOException;
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) throws DAOException;
	public Team TopScorerTeam() throws DAOException;
}
