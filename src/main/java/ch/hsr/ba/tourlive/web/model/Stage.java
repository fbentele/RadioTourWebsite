package ch.hsr.ba.tourlive.web.model;

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
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
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

	@NotEmpty
	@Column(name = "STAGE_NAME")
	private String stageName;

	/**
	 * A slug is a pathpart in an url.
	 */
	@NotEmpty
	@Column(unique = true)
	private String stageSlug;

	@Column(columnDefinition = "text")
	private String stageDescription;

	@Column
	private Long starttime;

	@Column
	private Long endtime;

	@Min(value = 0)
	@Column
	private Float distance;

	@Column
	private String bannerImage;

	@Column
	private String stageProfileImage;

	@Column(columnDefinition = "text")
	private String adCode;

	@Column
	private Boolean visible;

	@Column
	private Boolean completed;

	@ManyToOne
	@JoinColumn(name = "raceId")
	private Race race;

	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Device> devices;

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
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
			Date date = new SimpleDateFormat("dd.MM.yyyy - HH:mm").parse(unformatted);
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
			Date date = new SimpleDateFormat("dd.MM.yyyy - HH:mm").parse(unformatted);
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

	public Boolean getCompleted() {
		return System.currentTimeMillis() > endtime;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void addDevice(Device device) {
		devices.add(device);
	}

	public void removeDevice(Device device) {
		devices.remove(device);

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

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	@Override
	public String toString() {
		return "Stage [stageId=" + stageId + ", stageName=" + stageName + ", stageSlug="
				+ stageSlug + ", stageDescription=" + stageDescription + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", distance=" + distance + ", race=" + race
				+ ", devices=" + devices + "]";
	}

}
