package model;

import java.util.Date;

public class Match {
	Date MATCH_DATE;
	String HOME_TEAM_ABV;
	String AWAY_TEAM_ABV;
	int HOME_GOALS;
	int AWAY_GOALS;
	int HOME_RED_CARDS;
	int AWAY_RED_CARDS;
	String Referee;

	public Match(Date MATCH_DATE, String HOME_TEAM_ABV, String AWAY_TEAM_ABV, int HOME_GOALS, int AWAY_GOALS, int HOME_RED_CARDS, int AWAY_RED_CARDS, String referee) {
		this.MATCH_DATE = MATCH_DATE;
		this.HOME_TEAM_ABV = HOME_TEAM_ABV;
		this.AWAY_TEAM_ABV = AWAY_TEAM_ABV;
		this.HOME_GOALS = HOME_GOALS;
		this.AWAY_GOALS = AWAY_GOALS;
		this.HOME_RED_CARDS = HOME_RED_CARDS;
		this.AWAY_RED_CARDS = AWAY_RED_CARDS;
		Referee = referee;
	}

	public Date getMATCH_DATE() {
		return MATCH_DATE;
	}

	public void setMATCH_DATE(Date MATCH_DATE) {
		this.MATCH_DATE = MATCH_DATE;
	}

	public String getHOME_TEAM_ABV() {
		return HOME_TEAM_ABV;
	}

	public void setHOME_TEAM_ABV(String HOME_TEAM_ABV) {
		this.HOME_TEAM_ABV = HOME_TEAM_ABV;
	}

	public String getAWAY_TEAM_ABV() {
		return AWAY_TEAM_ABV;
	}

	public void setAWAY_TEAM_ABV(String AWAY_TEAM_ABV) {
		this.AWAY_TEAM_ABV = AWAY_TEAM_ABV;
	}

	public int getHOME_GOALS() {
		return HOME_GOALS;
	}

	public void setHOME_GOALS(int HOME_GOALS) {
		this.HOME_GOALS = HOME_GOALS;
	}

	public int getAWAY_GOALS() {
		return AWAY_GOALS;
	}

	public void setAWAY_GOALS(int AWAY_GOALS) {
		this.AWAY_GOALS = AWAY_GOALS;
	}

	public int getHOME_RED_CARDS() {
		return HOME_RED_CARDS;
	}

	public void setHOME_RED_CARDS(int HOME_RED_CARDS) {
		this.HOME_RED_CARDS = HOME_RED_CARDS;
	}

	public int getAWAY_RED_CARDS() {
		return AWAY_RED_CARDS;
	}

	public void setAWAY_RED_CARDS(int AWAY_RED_CARDS) {
		this.AWAY_RED_CARDS = AWAY_RED_CARDS;
	}

	public String getReferee() {
		return Referee;
	}

	public void setReferee(String referee) {
		Referee = referee;
	}
}
