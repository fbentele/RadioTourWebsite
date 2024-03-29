package ch.hsr.ba.tourlive.web.model.rider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.utils.DateUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
public class Rider {
	@Id
	@Column
	@GeneratedValue
	private Long riderId;
	@Column
	private Integer startNr;
	@Column
	private String name;
	@Column
	private String team;
	@Column
	private String teamShort;
	@Column
	private String country;
	@Column
	private String note;
	@Column
	private Boolean neo;
	/**
	 * Official Time
	 */
	@Column
	private Long timeOff;
	/**
	 * Deficite Time
	 */
	@Column
	private Long timeRueck;
	/**
	 * Virtual Time
	 */
	@Column
	private Long timeVirt;
	@JsonIgnore
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
		return startNr == null ? 0 : startNr;
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

	public String getTeamShort() {
		return teamShort;
	}

	public void setTeamShort(String teamShort) {
		this.teamShort = teamShort;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getTimeOff() {
		return DateUtil.toShortTimeFormat(timeOff);
	}

	public void setTimeOff(String timeOff) {
		this.timeOff = DateUtil.timeToTimestamp(timeOff);
	}

	public String getTimeRueck() {
		return DateUtil.toShortTimeFormat(timeRueck);
	}

	public void setTimeRueck(String timeRueck) {
		this.timeRueck = DateUtil.timeToTimestamp(timeRueck);
	}

	public String getTimeVirt() {
		return DateUtil.toShortTimeFormat(timeVirt);
	}

	public void setTimeVirt(String timeVirt) {
		this.timeVirt = DateUtil.timeToTimestamp(timeVirt);
	}

}
