package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
			cS.setString(1, oneTeam.getName());
			cS.setString(2, oneTeam.getAbv());			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int GoalsOfTeam(Team t) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int AwayGoals() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team TopScorerTeam() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws Exception {
		con.close();

	}
}