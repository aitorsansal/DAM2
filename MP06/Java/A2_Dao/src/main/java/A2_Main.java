import dao.DAOException;
import dao.DAOFactory;
import dao.DAOManager;
import model.Match;
import model.Team;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class A2_Main {

	public static void main(String[] args) throws DAOException, SQLException, ParseException {
		Team t = new Team("TST","Test Team","noLogo");
		DAOFactory fac = new DAOFactory();
		DAOManager dao = fac.createDAOManager();
		//dao.AddTeam(t);
		Team aa = dao.GetTeam("TST");
		System.out.println("GetTeam i AddTeam--> Expected (Test Team): " + aa.getName());
		System.out.println("GetTeamAbbreviation --> Expected (TST): " + dao.GetTeamAbbreviation("Test Team"));
		Team arsTeam = new Team("ARS","a","dnoLogo");
		Team cryTeam = new Team("CRY","a","noLogo");
		System.out.println("Goals of Arsenal: " + dao.GoalsOfTeam(arsTeam));
		System.out.println("Red cards of Arsenal: " + dao.RedCards(arsTeam));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString="2022-08-05";

		Date d = new Date(sdf.parse(dateString).getTime());
		Match m = dao.GetMatch(d,cryTeam,arsTeam);
		System.out.println("GetMatch Game: " + m.getMATCH_DATE() +"  " + m.getHOME_TEAM_ABV() + " vs " + m.getAWAY_TEAM_ABV());
		System.out.println("MATCHES OF TEAM ARSENAL");
		List<Match> llista = dao.MatchesOfTeam(arsTeam);
		llista.forEach(game ->
				System.out.println(game.getMATCH_DATE().toString() +  "\t" +
						game.getHOME_TEAM_ABV() + " " + game.getHOME_GOALS() + " /// "+
						game.getAWAY_GOALS() + " " + game.getAWAY_TEAM_ABV())
		);
		System.out.println("Total away goals: " + dao.AwayGoals());
		Team topScorer = dao.TopScorerTeam();
		System.out.println("Top scorer team: " + topScorer.getAbv() + ", "+ topScorer.getName() + ", goals: " + topScorer.getGoalsScored());
	}

}
