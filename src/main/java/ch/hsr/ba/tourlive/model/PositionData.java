package ch.hsr.ba.tourlive.model;

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
	private float longitude;

	@Column
	private float latitude;

	@Column
	private float speed;

	@Column
	private double altitude;

	@Column
	private float direction;

	@Column
	private int incline;

	@Column
	private String satellites;

	@Column
	private Integer gpssendinterval;

	public Long getPositionid() {
		return positionid;
	}

	public void setPositionid(Long positionid) {
		this.positionid = positionid;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getIncline() {
		return incline;
	}

	public void setIncline(int incline) {
		this.incline = incline;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	@Override
	public String toString() {
		return "The altitude is: " + altitude;
	}

	public String getSatellites() {
		return satellites;
	}

	public void setSatellites(String satellites) {
		this.satellites = satellites;
	}

	public int getGpssendinterval() {
		return gpssendinterval;
	}

	public void setGpssendinterval(int gpssendinterval) {
		this.gpssendinterval = gpssendinterval;
	}
}
