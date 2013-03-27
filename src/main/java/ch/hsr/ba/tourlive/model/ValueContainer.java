package ch.hsr.ba.tourlive.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "ValueContainer")
public class ValueContainer {
	@Id
	@GeneratedValue
	@Column(name = "VALUECONTAINER_ID")
	private Long valueContainerId;

	@Column
	private String deviceId;

	@Column
	private String username;

	@Column
	private Date timestamp;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITIONDATA_ID")
	private PositionData positionData;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STAGEDATA_ID")
	private StageData stageData;
	// @embedded

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NETDATA_ID")
	private NetData netData;

	public Long getValueContainerId() {
		return valueContainerId;
	}

	public void setValueContainerId(Long valueContainerId) {
		this.valueContainerId = valueContainerId;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

}
