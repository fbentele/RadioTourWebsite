package ch.hsr.ba.tourlive.web.model;

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
@Table(name = "Position")
public class PositionData {
	@Id
	@Column(name = "POSITION_ID")
	@GeneratedValue
	private Long positionid;

	@Column
	private Float longitude;

	@Column
	private Float latitude;

	@Column
	private Float speed;

	@Column
	private Integer altitude;

	@Column
	private Integer direction;

	@Column
	private String satellites;

	@Column
	private Integer accuracy;

	public Long getPositionid() {
		return positionid;
	}

	public void setPositionid(Long positionid) {
		this.positionid = positionid;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getSatellites() {
		return satellites;
	}

	public void setSatellites(String satellites) {
		this.satellites = satellites;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public String toString() {
		return "PositionData [positionid=" + positionid + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", speed=" + speed + ", altitude=" + altitude
				+ ", direction=" + direction + ", satellites=" + satellites + "]";
	}
}
