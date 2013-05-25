package ch.hsr.ba.tourlive.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "NetData")
public class NetData {
	@Id
	@GeneratedValue
	@Column(name = "NETDATA_ID")
	private int netDataID;
	@Column
	private Integer cellNumber;
	@Column
	private Integer locationArea;
	@Column
	private Integer signalStrength;
	@Column
	private String mncmcc;
	@Column
	private Integer upstream;
	@Column
	private String technology;

	public int getNetDataID() {
		return netDataID;
	}

	public void setNetDataID(Integer netDataID) {
		this.netDataID = netDataID;
	}

	public Integer getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(Integer cellNumber) {
		this.cellNumber = cellNumber;
	}

	public Integer getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(Integer locationArea) {
		this.locationArea = locationArea;
	}

	public Integer getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(Integer signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Integer getUpstream() {
		return upstream;
	}

	public void setUpstream(Integer upstream) {
		this.upstream = upstream;
	}

	public String getMncmcc() {
		return mncmcc;
	}

	public void setMncmcc(String mncmcc) {
		this.mncmcc = mncmcc;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	@Override
	public String toString() {
		return "NetData [netDataID=" + netDataID + ", cellNumber=" + cellNumber + ", locationArea="
				+ locationArea + ", signalStrength=" + signalStrength + ", mncmcc=" + mncmcc
				+ ", upstream=" + upstream + ", technology=" + technology + "]";
	}
}
