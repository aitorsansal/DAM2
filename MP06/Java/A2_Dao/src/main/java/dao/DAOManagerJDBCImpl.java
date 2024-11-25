package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Match;
import model.Team;

public class DAOManagerJDBCImpl implements DAOManager {
	
	private Connection con;
	
	public DAOManagerJDBCImpl() throws DAOException {
		try
		{
			String connectionUrl = "jdbc:mysql://localhost:3306/premier?serverTimezone=UTC";
			con = DriverManager.getConnection(connectionUrl, "root", "12345");
		}
		catch(Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean AddTeam(Team oneTeam) throws DAOException{
		try
		{
			String storedProcedureCall = "{call AddTeam(?,?,?)}";	
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, oneTeam.getAbv());
			cS.setString(2, oneTeam.getName());
			cS.setString(3, oneTeam.getLogo_link());
			cS.execute();
		}
		catch(Exception e) {
			throw new DAOException(e);
		}
		return false;
	}

	@Override
	public Team GetTeam(String teamAbreviation) throws DAOException {
		Team tm = null;
		try
		{
			String storedProcedureCall = "{call GetTeamByAbreviation(?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, teamAbreviation);
			boolean hasResults = cS.execute();
			if(hasResults) {
				ResultSet resSet = cS.getResultSet();
				resSet.next();
				tm = new Team(teamAbreviation, resSet.getString(1), resSet.getString(2));
			}
			
		}catch(Exception e) {
			throw new DAOException(e);
			
		}
		return tm;
	}

	@Override
	public String GetTeamAbbreviation(String teamName) throws DAOException {
		String abv = null;
		try
		{
			String storedProcedureCall = "{call GetAbvByName(?,?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, teamName);
			cS.registerOutParameter(2, java.sql.Types.VARCHAR);
			cS.execute();
			abv = cS.getString(2);
		} catch (Exception e){
			throw new DAOException(e);
		}
		return abv;
	}

	@Override
	public Match GetMatch(Date matchDay, Team homeTeam, Team awayTeam) throws DAOException {

		Match match = null;
		try{
			String storedProcedureCall ="{call GetMatch(?,?,?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setDate(1, matchDay);
			cS.setString(2, homeTeam.getAbv());
			cS.setString(3, awayTeam.getAbv());
			boolean hasResults = cS.execute();
			if(hasResults) {
				ResultSet resSet = cS.getResultSet();
				resSet.next();
				match = new Match(matchDay, homeTeam.getAbv(), awayTeam.getAbv(),
						resSet.getInt(1), resSet.getInt(2), resSet.getInt(3), resSet.getInt(4),
						resSet.getString(5));
			}

		}catch(Exception e){
			throw new DAOException(e);
		}
		return match;
	}

	@Override
	public int GoalsOfTeam(Team t) throws DAOException {
		int totalGoals = 0;
		try{
			String storedProcedureCall ="{call GetTotalGoals(?,?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, t.getAbv());
			cS.registerOutParameter(2, Types.INTEGER);
			cS.execute();
			totalGoals =  cS.getInt(2);

		}catch(Exception e){
			throw new DAOException(e);
		}
		return totalGoals;
	}

	@Override
	public int AwayGoals() throws DAOException {
		int totalGoals = 0;
		try{
			String storedProcedureCall ="{call GetTotalAwayGoals(?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.registerOutParameter(1, Types.INTEGER);
			cS.execute();
			totalGoals =  cS.getInt(1);

		}catch(Exception e){
			throw new DAOException(e);
		}
		return totalGoals;
	}

	@Override
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) throws DAOException {
		ArrayList<Match> matches = new ArrayList<Match>();
		try{
			String storedProcedureCall ="{call GetMatchesOfTeam(?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, oneTeam.getAbv());
			boolean hasResults = cS.execute();
			if(hasResults) {
				ResultSet resSet = cS.getResultSet();
				while(resSet.next()) {
					matches.add(new Match(resSet.getDate(1), resSet.getString(2),resSet.getString(3),
							resSet.getInt(4), resSet.getInt(5),
							resSet.getInt(6), resSet.getInt(7), resSet.getString(8)));
				}
			}

		}catch(Exception e){
			throw new DAOException(e);
		}
		return matches;
	}

	@Override
	public int RedCards(Team oneTeam) throws DAOException {
		int totalGoals = 0;
		try{
			String storedProcedureCall ="{call GetRedCards(?,?)}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			cS.setString(1, oneTeam.getAbv());
			cS.registerOutParameter(2, Types.INTEGER);
			cS.execute();
			totalGoals =  cS.getInt(2);

		}catch(Exception e){
			throw new DAOException(e);
		}
		return totalGoals;
	}

	@Override
	public Team TopScorerTeam() throws DAOException {
		Team team = null;
		try{
			String storedProcedureCall ="{call TopScorer()}";
			CallableStatement cS = con.prepareCall(storedProcedureCall);
			boolean hasResults = cS.execute();
			if(hasResults) {
				ResultSet resSet = cS.getResultSet();
				resSet.next();
				team = new Team(resSet.getString(2), resSet.getString(1), resSet.getString(3));
				team.setGoalsScored(resSet.getInt(4));
			}

		}catch(Exception e){
			throw new DAOException(e);
		}
		return team;
	}

	@Override
	public void close() throws Exception {
		con.close();
	}
}
