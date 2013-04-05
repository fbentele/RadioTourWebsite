package ch.hsr.ba.tourlive.model;

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

	public Long getImageDataId() {
		return imageDataId;
	}

	public void setImageDataId(Long imageDataId) {
		this.imageDataId = imageDataId;
	}

	public Long getTimestamp() {
		return timestamp;
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
