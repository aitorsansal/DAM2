package test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Team;

public class MainHibernateTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORMFutbol");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction t = null;
		t = entityManager.getTransaction();
		t.begin();
		Team team = new Team();
		team.setAbv("ABC");
		team.setClubName("CLUB");
		team.setLogoLink("noPhotoLink");
		entityManager.persist(team);
		t.commit();
		entityManager.close();
		emf.close();
	}

}
