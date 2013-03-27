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
	private String mncmcc;
	@Column
	private int upspeed;
	@Column
	private int rtt;
	@Column
	private int packetloss;
	@Column
	private String technology;

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

	public int getUpspeed() {
		return upspeed;
	}

	public void setUpspeed(int upspeed) {
		this.upspeed = upspeed;
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

}
