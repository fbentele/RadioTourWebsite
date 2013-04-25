package ch.hsr.ba.tourlive.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "ImageData")
public class ImageData {
	@Id
	@GeneratedValue
	@Column
	private Long imageDataId;
	@Column
	private Long timestamp;
	@ManyToOne
	@JoinColumn(name = "deviceId")
	private Device device;
	@Column
	private String imageLocation;

	public ImageData(Long timestamp, Device device, String imageLocation) {
		this.timestamp = timestamp;
		this.device = device;
		this.imageLocation = imageLocation;
	}

	public ImageData() {

	}

	public Long getImageDataId() {
		return imageDataId;
	}

	public void setImageDataId(Long imageDataId) {
		this.imageDataId = imageDataId;
	}

	public Long getRealTimestamp() {
		return timestamp;
	}

	public String getTimestamp() {
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		try {
			Date d = new Date(this.timestamp);
			return date.format(d);
		} catch (NullPointerException e) {
			return "Zeitpunkt unbekannt";
		}
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
}
