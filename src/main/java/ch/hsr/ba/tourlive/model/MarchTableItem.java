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
	private Float distance;
	@Column
	private Float distanceToGo;
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

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getDistanceToGo() {
		return distanceToGo;
	}

	public void setDistanceToGo(Float distanceToGo) {
		this.distanceToGo = distanceToGo;
	}

	public String getOrtschaft() {
		return ortschaft;
	}

	public void setOrtschaft(String ortschaft) {
		this.ortschaft = ortschaft;
	}

	public String getWerbekolonne() {
		return DateUtil.toTimeFormat(werbekolonne);
	}

	public void setWerbekolonne(String werbekolonne) {
		this.werbekolonne = DateUtil.timeToTimestamp(werbekolonne);
	}

	public String getRaceSlow() {
		return DateUtil.toTimeFormat(raceSlow);
	}

	public void setRaceSlow(String raceSlow) {
		this.raceSlow = DateUtil.timeToTimestamp(raceSlow);
	}

	public String getRaceMedium() {
		return DateUtil.toTimeFormat(raceMedium);
	}

	public void setRaceMedium(String raceMedium) {
		this.raceMedium = DateUtil.timeToTimestamp(raceMedium);
	}

	public String getRaceFast() {
		return DateUtil.toTimeFormat(raceFast);
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
