package ch.hsr.ba.tourlive.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Stage")
public class Stage {
	@Id
	@Column(name = "STAGE_ID")
	@GeneratedValue
	private Long stageId;

	@Column(name = "STAGE_NAME")
	private String stageName;

	@Column
	private String stageDescription;

	@Column
	private Long starttime;

	@Column
	private Long endtime;

	@Column
	private float distance;

	@ManyToOne
	@JoinColumn(name = "raceId")
	private Race race;

	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Device> devices;

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getStageDescription() {
		return stageDescription;
	}

	public void setStageDescription(String stageDescription) {
		this.stageDescription = stageDescription;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

}
