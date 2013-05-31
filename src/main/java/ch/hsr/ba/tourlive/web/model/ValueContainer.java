package ch.hsr.ba.tourlive.web.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.web.utils.DateUtil;

/**
 * The ValueContainer combines information about the current position of a
 * device, the current stage and mobile networking information.
 * 
 * @author fbentele
 * 
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "ValueContainer")
public class ValueContainer {
	@Id
	@GeneratedValue
	@Column(name = "VALUECONTAINER_ID")
	private Long valueContainerId;

	@Column(columnDefinition = "bigint(20)")
	private Long timestamp;

	@Column
	private Long deficiteTime;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "POSITIONDATA_ID")
	private PositionData positionData;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "STAGEDATA_ID")
	private StageData stageData;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "NETDATA_ID")
	private NetData netData;

	public Long getValueContainerId() {
		return valueContainerId;
	}

	public void setValueContainerId(Long valueContainerId) {
		this.valueContainerId = valueContainerId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getDeficiteTimeAsTimestamp() {
		return deficiteTime;
	}

	public Long getDeficiteTime() {
		return deficiteTime;
	}

	public String getDeficiteTimeAsString() {
		return DateUtil.toShortTimeFormat(deficiteTime);
	}

	public void setDeficiteTime(Long deficiteTime) {
		this.deficiteTime = deficiteTime;
	}

	public PositionData getPositionData() {
		return positionData;
	}

	public void setPositionData(PositionData positionData) {
		this.positionData = positionData;
	}

	public StageData getStageData() {
		return stageData;
	}

	public void setStageData(StageData stageData) {
		this.stageData = stageData;
	}

	public NetData getNetData() {
		return netData;
	}

	public void setNetData(NetData netData) {
		this.netData = netData;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "ValueContainer [valueContainerId=" + valueContainerId + ", timestamp=" + timestamp
				+ ", device=" + device + ", positionData=" + positionData + ", stageData="
				+ stageData + ", netData=" + netData + "]";
	}

}
