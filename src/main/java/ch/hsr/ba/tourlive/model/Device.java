package ch.hsr.ba.tourlive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Device")
public class Device {
	@Id
	@Column(name = "DEVICE_ID")
	private String deviceId;

	@Column
	private String username;

	@Column
	private String color;

	@Column
	private String labelName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", username=" + username + "]";
	}

	@Override
	public boolean equals(Object o) {
		return this.deviceId.equals(((Device) o).deviceId);
	}

	@Override
	public int hashCode() {
		return this.hashCode();
	}
}