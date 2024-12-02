package DAO;

import model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOManagerHibernateImplementation implements DAOManager {

    EntityManagerFactory emf;
    EntityManager entityManager;

    public DAOManagerHibernateImplementation() {
        emf = Persistence.createEntityManagerFactory("ORMFutbol");
        entityManager = emf.createEntityManager();
    }

    @Override
    public boolean addTeam(Team oneTeam) {
        EntityTransaction t = entityManager.getTransaction();
        try{
            t.begin();
            entityManager.persist(oneTeam);
            t.commit();
        }
        catch (Exception e){
            t.rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTeam(String teamAbbv) {
        EntityTransaction t = entityManager.getTransaction();
        try{
            t.begin();
            Team team = getTeamByAbbr(teamAbbv);

            if (team == null) {
                t.rollback();
                return false;
            }
            entityManager.remove(team);
            t.commit();
        }
        catch (Exception e){
            if(t.isActive())
                t.rollback();
            return false;
        }
        return true;
    }

    @Override
    public Team updateTeam(Team oneTeam) {
        EntityTransaction t = entityManager.getTransaction();
        Team team = getTeamByAbbr(oneTeam.getAbv());
        try{
            t.begin();
            if(team == null){
                t.rollback();
                return null;
            }
            team.setClubName(oneTeam.getClubName());
            team.setLogoLink(oneTeam.getLogoLink());
            team.setHexCode(oneTeam.getHexCode());
            t.commit();
        }
        catch (Exception e){
            t.rollback();
            return null;
        }
        return team;
    }

    @Override
    public Team getTeamByAbbr(String abbr) {
        try {
            return (Team) entityManager.createQuery("SELECT t FROM Team t WHERE t.abv = :abbr")
                    .setParameter("abbr", abbr)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Team getTeamByName(String name) {
        try {
            return (Team) entityManager.createQuery("SELECT t FROM Team t WHERE t.clubName = :name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Team> getAllTeams() {
        try {
            return (ArrayList<Team>) entityManager.createQuery("SELECT t FROM Team t").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        emf.close();
    }
}
