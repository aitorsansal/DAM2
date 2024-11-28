package model;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	public Team() {}
	
	
	
}
