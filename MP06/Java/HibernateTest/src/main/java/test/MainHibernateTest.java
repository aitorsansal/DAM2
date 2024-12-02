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

		Team teamToUpdate = new Team();
		teamToUpdate.setAbv("ABC");
		teamToUpdate.setLogoLink("aaaaaaaaaaa");
		daoManager.updateTeam(teamToUpdate);
		Team tm = daoManager.getTeamByAbbr("ABC");
		System.out.println(tm.getLogoLink());
	}

}
