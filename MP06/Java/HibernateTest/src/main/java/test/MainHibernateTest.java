package test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DAO.DAOManager;
import DAO.DAOManagerHibernateImplementation;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainHibernateTest {

	public static void main(String[] args) {
		DAOManager daoManager = new DAOManagerHibernateImplementation();

		Team teamToUpdate = new Team();
		teamToUpdate.setAbv("TTT");
		teamToUpdate.setLogoLink("aaaaaaaaaaa");
		teamToUpdate.setClubName("Testing Team");
		Player player1 = new Player("TTT", 1, "A", "TE");
		Player player2 = new Player("TTT", 2, "B", "CA");
		Player player3 = new Player("TTT", 3, "C", "WA");
		Player player4 = new Player("TTT", 4, "D", "DW");
		Player player5 = new Player("TTT", 5, "E", "PW");
		List<Player> players = List.of(player1, player2, player3, player4, player5);
		daoManager.addTeam(teamToUpdate, players);
	}

}
