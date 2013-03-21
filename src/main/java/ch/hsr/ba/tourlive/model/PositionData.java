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
	@Column(name = "POSITIONDATA_ID")
	@GeneratedValue
	private Long positionid;

	@Column
	private int timestamp;

	@Column
	private float longitude;

	@Column
	private float latitude;

	@Column
	private Long altitude;

	@Column
	private float speed;

	@Column
	private int direction;

	@Column
	private int incline;

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDirection() {
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

	public Long getAltitude() {
		return altitude;
	}

	public void setAltitude(Long altitude) {
		this.altitude = altitude;
	}

	@Override
	public String toString() {
		return "Timestamp: " + timestamp + " and altitude: " + altitude;
	}
}
