package ch.hsr.ba.tourlive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.utils.DateUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "MarchTableItem")
public class MarchTableItem {
	@Id
	@Column
	@GeneratedValue
	private Long marchTableItemId;
	@Column
	private String icon;
	@Column
	private Integer altitude;
	@Column
	private Double distance;
	@Column
	private Double distanceToGo;
	@Column
	private String ortschaft;
	@Column
	private Long werbekolonne;
	@Column
	private Long raceSlow;
	@Column
	private Long raceMedium;
	@Column
	private Long raceFast;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "stageId")
	private Stage stage;

	public Long getMarchTableItemId() {
		return marchTableItemId;
	}

	public void setMarchTableItemId(Long marchTableItemId) {
		this.marchTableItemId = marchTableItemId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistanceToGo() {
		return distanceToGo;
	}

	public void setDistanceToGo(Double distanceToGo) {
		this.distanceToGo = distanceToGo;
	}

	public String getOrtschaft() {
		return ortschaft;
	}

	public void setOrtschaft(String ortschaft) {
		this.ortschaft = ortschaft;
	}

	public Long getWerbekolonne() {
		return werbekolonne;
	}

	public void setWerbekolonne(String werbekolonne) {
		this.werbekolonne = DateUtil.timeToTimestamp(werbekolonne);
	}

	public Long getRaceSlow() {
		return raceSlow;
	}

	public void setRaceSlow(String raceSlow) {
		this.raceSlow = DateUtil.timeToTimestamp(raceSlow);
	}

	public Long getRaceMedium() {
		return raceMedium;
	}

	public void setRaceMedium(String raceMedium) {
		this.raceMedium = DateUtil.timeToTimestamp(raceMedium);
	}

	public Long getRaceFast() {
		return raceFast;
	}

	public void setRaceFast(String raceFast) {
		this.raceFast = DateUtil.timeToTimestamp(raceFast);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
