package ch.hsr.ba.tourlive.web.model;

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

	/**
	 * The Phone Number of the device (usually not callable, since data contract
	 * only)
	 */
	@Column
	private String phoneNr;

	/**
	 * The Phone Number of the driver who is carrying the phone
	 */
	@Column
	private String driverPhoneNr;
	@Column
	private String driverName;

	public Device() {

	}

	public Device(String devid) {
		this.deviceId = devid;
	}

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

	public String getColorAsRGB() {
		try {
			StringBuilder rgb = new StringBuilder();
			if (color.length() > 6) {
				rgb.append(Integer.valueOf(color.substring(1, 3), 16) + ",");
				rgb.append(Integer.valueOf(color.substring(3, 5), 16) + ",");
				rgb.append(Integer.valueOf(color.substring(5, 7), 16) + ",");
			}
			return rgb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public void setColor(String color) {
		if (color.length() > 0) {
			this.color = color;
		}
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public String getDriverPhoneNr() {
		return driverPhoneNr;
	}

	public void setDriverPhoneNr(String driverPhoneNr) {
		this.driverPhoneNr = driverPhoneNr;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", username=" + username + "]";
	}
}