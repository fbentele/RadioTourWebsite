package ch.hsr.ba.tourlive.web.model.rider;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Situation implements Serializable {
	private static final long serialVersionUID = -5090788260118136922L;
	private int[] riderNumber;
	private boolean isLeader;
	private int groupnr;
	private Long handicaptime;
	private boolean isField;

	public Situation() {
	}

	public Situation(int[] drivernumber, boolean isLeader, int groupnr, Long handicaptime,
			boolean isField) {
		super();
		this.riderNumber = drivernumber;
		this.isLeader = isLeader;
		this.groupnr = groupnr;
		this.handicaptime = handicaptime;
		this.isField = isField;
	}

	public int[] getRiderNumber() {
		return riderNumber;
	}

	public void setRiderNumber(int[] riderNumber) {
		this.riderNumber = riderNumber;
	}

	public boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public int getGroupnr() {
		return groupnr;
	}

	public void setGroupnr(int groupnr) {
		this.groupnr = groupnr;
	}

	public String getGroupName() {
		if (isLeader)
			return "Spitze";
		if (isField)
			return "Feld";
		return "Gruppe " + groupnr;
	}

	public String getGroupSize() {
		int groupSize;
		try {
			groupSize = riderNumber.length;
		} catch (NullPointerException e) {
			groupSize = 0;
		}
		if (groupSize < 1)
			return isField ? "large" : "none";
		if (groupSize == 1)
			return "one";
		if (groupSize == 2)
			return "two";
		if (groupSize < 5)
			return "small";
		if (groupSize < 10)
			return "medium";
		if (groupSize >= 10)
			return "large";
		return "none";
	}

	public Long getHandicaptimeAsTimestamp() {
		return handicaptime;
	}

	public String getHandicaptime() {
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		try {
			Date d = new Date(this.handicaptime);
			return date.format(d);
		} catch (NullPointerException e) {
			return "00:00";
		}
	}

	public void setHandicaptime(Long handicaptime) {
		this.handicaptime = handicaptime;
	}

	public boolean getIsField() {
		return isField;
	}

	public void setIsField(boolean isField) {
		this.isField = isField;
	}

}