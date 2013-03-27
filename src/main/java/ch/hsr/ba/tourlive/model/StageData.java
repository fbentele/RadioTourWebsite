package ch.hsr.ba.tourlive.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Stagedata")
public class StageData implements Serializable {
	private static final long serialVersionUID = -6609689730044394749L;

	@Id
	@GeneratedValue
	@Column(name = "STAGEDATA_ID")
	private Long stagedataid;

	@Column
	private String stagetime;

	@Column
	private int stagealtitude;

	@Column
	private int distance;

	@Column
	private float averagespeed;

	public Long getStagedataid() {
		return stagedataid;
	}

	public void setStagedataid(Long stagedataid) {
		this.stagedataid = stagedataid;
	}

	public String getStagetime() {
		return stagetime;
	}

	public void setStagetime(String stagetime) {
		this.stagetime = stagetime;
	}

	public int getStagealtitude() {
		return stagealtitude;
	}

	public void setStagealtitude(int stagealtitude) {
		this.stagealtitude = stagealtitude;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public float getAveragespeed() {
		return averagespeed;
	}

	public void setAveragespeed(float averagespeed) {
		this.averagespeed = averagespeed;
	}
}
