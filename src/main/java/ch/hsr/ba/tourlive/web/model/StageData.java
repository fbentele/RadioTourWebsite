package ch.hsr.ba.tourlive.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * StageData provides information about the current position within a
 * {@link Stage} (collected by a {@link Device} and inserted into a
 * {@link ValueContainer}
 * 
 * @author flo
 * 
 */

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
	private Double stageUpAltitude;

	@Column
	private Float distance;

	@Column
	private Float averageSpeed;

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

	public Double getStageUpAltitude() {
		if (stageUpAltitude != null) {
			return (double) (Math.round(stageUpAltitude * 100) / 100.0);
		} else {
			return null;
		}
	}

	public void setStageUpAltitude(Double stageUpAltitude) {
		this.stageUpAltitude = stageUpAltitude;
	}

	public Float getDistance() {
		return (float) (Math.round(distance * 100) / 100.0);
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getAverageSpeed() {
		return (float) (Math.round(averageSpeed * 100) / 100.0);
	}

	public void setAverageSpeed(Float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StageData [stagedataid=" + stagedataid + ", stageTime=" + stageTime
				+ ", stageAltitude=" + stageUpAltitude + ", distance=" + distance
				+ ", averageSpeed=" + averageSpeed + "]";
	}

}