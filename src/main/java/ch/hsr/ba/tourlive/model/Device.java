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
	private String deviceDescription;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceid) {
		this.deviceId = deviceid;
	}

	public String getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(String devicedescription) {
		this.deviceDescription = devicedescription;
	}
}
