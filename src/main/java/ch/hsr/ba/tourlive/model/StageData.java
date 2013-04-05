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
	private String stageTime;

	@Column
	private double stageUpAltitude;

	@Column
	private float distance;

	@Column
	private float averageSpeed;

	public Long getStagedataid() {
		return stagedataid;
	}

	public void setStagedataid(Long stagedataid) {
		this.stagedataid = stagedataid;
	}

	public String getStageTime() {
		return stageTime;
	}

	public void setStageTime(String stageTime) {
		this.stageTime = stageTime;
	}

	public double getStageUpAltitude() {
		return stageUpAltitude;
	}

	public void setStageUpAltitude(double stageUpAltitude) {
		this.stageUpAltitude = stageUpAltitude;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StageData [stagedataid=" + stagedataid + ", stageTime="
				+ stageTime + ", stageAltitude=" + stageUpAltitude
				+ ", distance=" + distance + ", averageSpeed=" + averageSpeed
				+ "]";
	}

}
