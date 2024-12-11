package test;


import DAO.DAOFactory;
import DAO.DAOManager;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainHibernateTest {

	public static void main(String[] args) {
		try
		{
			DAOManager daoManager = DAOFactory.createDAOManager();
			daoManager.clearDBForTesting();

			System.out.println("Creation of new Team with it's players");
			Team teamToUpdate = new Team();
			teamToUpdate.setAbv("XXX");
			teamToUpdate.setLogoLink("aaaaaaaa");
			teamToUpdate.setClubName("Testing Team");
			Player player1 = new Player("XXX", 1, "player 1", "TE");
			Player player2 = new Player("XXX", 2, "player 2", "CA");
			Player player3 = new Player("XXX", 3, "player 3", "WA");
			Player player4 = new Player("XXX", 4, "player 4", "DW");
			Player player5 = new Player("XXX", 5, "player 5", "PW");
			List<Player> players = List.of(player1, player2, player3, player4, player5);
			daoManager.addTeam(teamToUpdate, players);
			System.out.println("------------------------");
			System.out.println("Retriving players from new added team XXX");
			daoManager.getTeamPlayers("XXX").forEach(p ->
					System.out.println(p.getName())
			);
			System.out.println("------------------------");
			System.out.println("Update team");
			Team t = daoManager.getTeamByAbbr("ARS");
			t.setLogoLink("deleted logo link");
			daoManager.updateTeam(t);
			Team t2 = daoManager.getTeamByAbbr("ARS");
			System.out.println(t2.getClubName() + ", " + t2.getLogoLink());
			System.out.println("------------------------");
			System.out.println("Show all the teams in the DB");
			ArrayList<Team> teams = daoManager.getAllTeams();
			teams.forEach(a ->
					System.out.println(a.getAbv() + ", " + a.getClubName())
			);

			int qnt = daoManager.importPlayers("PLAYERS.TXT");
			System.out.println("Quantity of players imported: " + qnt);
		} catch (Exception e){
			System.out.println(e);
		}
	}

}
