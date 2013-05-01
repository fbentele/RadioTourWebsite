package ch.hsr.ba.tourlive.model.rider;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.model.Stage;

@Component
@Scope("prototype")
@Entity
public class Rider {
	@Id
	@GeneratedValue
	private Long riderId;
	@Column
	private int startNr;
	@Column
	private String name;
	@Column
	private String team;
	@Column
	private String country;
	@Column
	private Date birthday;
	@Column
	private String note;
	@Column
	private Boolean neo;
	@ManyToOne
	@JoinColumn(name = "stageId")
	private Stage stage;

	public Long getRiderId() {
		return riderId;
	}

	public void setRiderId(Long riderId) {
		this.riderId = riderId;
	}

	public int getStartNr() {
		return startNr;
	}

	public void setStartNr(int startNr) {
		this.startNr = startNr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getNeo() {
		return neo;
	}

	public void setNeo(Boolean neo) {
		this.neo = neo;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
