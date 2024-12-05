package DAO;

import model.Player;
import model.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

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
    public boolean AddPlayer(Player onePlayer) {
        EntityTransaction t = entityManager.getTransaction();
        try{
            t.begin();
            entityManager.persist(onePlayer);
            t.commit();
        }
        catch (Exception e){
            t.rollback();
            return false;
        }
        return true;
    }

    @Override
    public int ImportPlayers(String playersFileName) {
        int quantOfPlayers = 0;
        try{
            File myFile = new File(playersFileName);
            Scanner reader = new Scanner(myFile);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                String[] spData = data.split(";");
                Integer id = Integer.parseInt(spData[0]);
                String name = spData[1];
                String teamAbv = getTeamByAbbr(spData[2]).getAbv();
                Integer height = null;
                if(!spData[4].equals("???"))
                    height = Integer.parseInt(spData[4].split(" ")[0]);
                String position = spData[5];
                Player player;
                if(height == null){
                    player = new Player(teamAbv, id, name, position);
                }
                else
                    player = new Player(teamAbv, id, name, height, position);

                if(AddPlayer(player))
                    quantOfPlayers++;
            }
            reader.close();
        }
        catch (Exception e){
        }
        return quantOfPlayers;
    }

    @Override
    public List<Player> getTeamPlayers(String teamAbv) {
        return new ArrayList<>(getTeamByAbbr(teamAbv).getPlayers());
    }

    @Override
    public boolean addTeam(Team oneTeam, List<Player> plantilla) {
        oneTeam.setPlayers(new HashSet<>(plantilla));
        return addTeam(oneTeam);
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        emf.close();
    }
}
