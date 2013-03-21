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
	private int cellnumber;
	@Column
	private int locationarea;
	@Column
	private int signalstrength;
	@Column
	private String network;
	@Column
	private String networkid;
	@Column
	private int upspeed;
	@Column
	private int downspeed;
	@Column
	private int rtt;
	@Column
	private int packetloss;

	public int getNetDataID() {
		return netDataID;
	}

	public void setNetDataID(int netDataID) {
		this.netDataID = netDataID;
	}

	public int getCellnumber() {
		return cellnumber;
	}

	public void setCellnumber(int cellnumber) {
		this.cellnumber = cellnumber;
	}

	public int getLocationarea() {
		return locationarea;
	}

	public void setLocationarea(int locationarea) {
		this.locationarea = locationarea;
	}

	public int getSignalstrength() {
		return signalstrength;
	}

	public void setSignalstrength(int signalstrength) {
		this.signalstrength = signalstrength;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getNetworkid() {
		return networkid;
	}

	public void setNetworkid(String networkid) {
		this.networkid = networkid;
	}

	public int getUpspeed() {
		return upspeed;
	}

	public void setUpspeed(int upspeed) {
		this.upspeed = upspeed;
	}

	public int getDownspeed() {
		return downspeed;
	}

	public void setDownspeed(int downspeed) {
		this.downspeed = downspeed;
	}

	public int getRtt() {
		return rtt;
	}

	public void setRtt(int rtt) {
		this.rtt = rtt;
	}

	public int getPacketloss() {
		return packetloss;
	}

	public void setPacketloss(int packetloss) {
		this.packetloss = packetloss;
	}

}
