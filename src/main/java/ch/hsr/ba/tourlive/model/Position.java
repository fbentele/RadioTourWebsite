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
public class Position {
	@Id
	@Column(name = "POSITION_ID")
	@GeneratedValue
	private Long positionId;

	@Column(name="LONGITUDE")
	private float longitude;
	
	@Column(name="LATITUDE")
	private float latitude;
	
	@Column(name="ALTITUDE")
	private Long altitude;

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
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
	
	
	
	
}
