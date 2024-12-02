package test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import DAO.DAOManager;
import DAO.DAOManagerHibernateImplementation;
import model.Team;

import java.util.ArrayList;

public class MainHibernateTest {

	public static void main(String[] args) {
		DAOManager daoManager = new DAOManagerHibernateImplementation();
		ArrayList<Team> t = daoManager.getAllTeams();

		for (Team team : t) {
			System.out.println(team.getClubName());
		}
	}

}
