package ch.hsr.ba.tourlive.model;

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
	private int cellNumber;
	@Column
	private int locationArea;
	@Column
	private int signalStrength;
	@Column
	private String mncmcc;
	@Column
	private int upstream;
	@Column
	private int rtt;
	@Column
	private int packetLoss;
	@Column
	private String technology;

	public int getNetDataID() {
		return netDataID;
	}

	public void setNetDataID(int netDataID) {
		this.netDataID = netDataID;
	}

	public int getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(int cellNumber) {
		this.cellNumber = cellNumber;
	}

	public int getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(int locationArea) {
		this.locationArea = locationArea;
	}

	public int getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}

	public int getUpstream() {
		return upstream;
	}

	public void setUpstream(int upstream) {
		this.upstream = upstream;
	}

	public int getRtt() {
		return rtt;
	}

	public void setRtt(int rtt) {
		this.rtt = rtt;
	}

	public int getPacketLoss() {
		return packetLoss;
	}

	public void setPacketLoss(int packetLoss) {
		this.packetLoss = packetLoss;
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
		return "NetData [netDataID=" + netDataID + ", cellNumber=" + cellNumber
				+ ", locationArea=" + locationArea + ", signalStrength="
				+ signalStrength + ", mncmcc=" + mncmcc + ", upstream="
				+ upstream + ", rtt=" + rtt + ", packetLoss=" + packetLoss
				+ ", technology=" + technology + "]";
	}

}
