package ch.hsr.ba.tourlive.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Stage")
public class Stage {
	@Id
	@Column(name = "STAGE_ID")
	@GeneratedValue
	private Long stageId;

	@Column(name = "STAGE_NAME")
	private String stageName;

	/*
	 * A slug is a pathpart in an url
	 */
	@Column(unique = true)
	private String stageSlug;

	@Column
	private String stageDescription;

	@Column
	private Long starttime;

	@Column
	private Long endtime;

	@Column
	private float distance;

	@Column
	private String bannerImage;

	@Column
	private String stageProfileImage;

	@ManyToOne
	@JoinColumn(name = "raceId")
	private Race race;

	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Device> devices;

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageSlug() {
		return stageSlug;
	}

	public void setStageSlug(String stageSlug) {
		this.stageSlug = stageSlug;
	}

	public String getStarttime() {
		SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
		try {
			Date d = new Date(this.starttime);
			return date.format(d);
		} catch (NullPointerException e) {
			return "01.01.1970 - 00:00";
		}
	}

	public Long getStarttimeAsTimestamp() {
		return this.starttime;
	}

	public Long getEndtimeAsTimestamp() {
		return this.endtime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public void setStarttime(String unformatted) {
		try {
			Date date = new SimpleDateFormat("dd.MM.yyyy - HH:mm")
					.parse(unformatted);
			this.starttime = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getEndtime() {
		SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
		try {
			Date d = new Date(this.endtime);
			return date.format(d);
		} catch (NullPointerException e) {
			return "01.01.1970 - 00:00";
		}
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public void setEndtime(String unformatted) {
		try {
			Date date = new SimpleDateFormat("dd.MM.yyyy - HH:mm")
					.parse(unformatted);
			this.endtime = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getStageDescription() {
		return stageDescription;
	}

	public void setStageDescription(String stageDescription) {
		this.stageDescription = stageDescription;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public void addDevice(Device device) {
		this.devices.add(device);
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getStageProfileImage() {
		return stageProfileImage;
	}

	public void setStageProfileImage(String stageProfileImage) {
		this.stageProfileImage = stageProfileImage;
	}

	@Override
	public String toString() {
		return "Stage [stageId=" + stageId + ", stageName=" + stageName
				+ ", stageSlug=" + stageSlug + ", stageDescription="
				+ stageDescription + ", starttime=" + starttime + ", endtime="
				+ endtime + ", distance=" + distance + ", race=" + race
				+ ", devices=" + devices + "]";
	}

}
