package model;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Table(name="team")
@Entity
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name="club_name")
	private String clubName;
	@Column(name="abv")
	@Id
	private String abv;
	@Column(name="logo_link")
	private String logoLink;
	@Column(name="hex_code")
	private String hexCode;
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getAbv() {
		return abv;
	}
	public void setAbv(String abv) {
		this.abv = abv;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
	public String getHexCode() {
		return hexCode;
	}
	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}
	
	@OneToMany(targetEntity = Player.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "abv")
	private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

	public Team() {}

	public Team(String clubName, String abv, String logoLink, String hexCode) {
		this.clubName = clubName;
		this.abv = abv;
		this.logoLink = logoLink;
		this.hexCode = hexCode;
	}

	public Team(String clubName, String abv, String logoLink, String hexCode, Set<Player> players) {
		this.clubName = clubName;
		this.abv = abv;
		this.logoLink = logoLink;
		this.hexCode = hexCode;
		this.players = players;
	}
}
