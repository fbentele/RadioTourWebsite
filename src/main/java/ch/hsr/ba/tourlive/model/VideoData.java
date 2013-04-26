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
@Table(name = "VideoData")
public class VideoData {
	@Id
	@GeneratedValue
	@Column
	private Long videoDataId;
	@Column
	private Long timestamp;
	@ManyToOne
	@JoinColumn(name = "deviceId")
	private Device device;
	@Column
	private String videoLocation;

	public VideoData(Long timestamp, Device device, String imageLocation) {
		this.timestamp = timestamp;
		this.device = device;
		this.videoLocation = imageLocation;
	}

	public VideoData() {

	}

	public Long getVideoDataId() {
		return videoDataId;
	}

	public void setVideoDataId(Long videoDataId) {
		this.videoDataId = videoDataId;
	}

	public String getVideoLocation() {
		return videoLocation;
	}

	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
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
}
