package ch.hsr.ba.tourlive.model.rider;

public class Group {
	private Boolean isField;
	private Boolean isLeader;
	private Long handicapTime;
	private int orderNumber;

	public Group(boolean field, boolean leader, Long handicap, int order) {
		this.isField = field;
		this.isLeader = leader;
		this.handicapTime = handicap;
		this.orderNumber = order;
	}

	public Group() {

	}

	public boolean isField() {
		return isField;
	}

	public void setField(boolean isField) {
		this.isField = isField;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public Long getHandicapTime() {
		return handicapTime;
	}

	public void setHandicapTime(Long handicapTime) {
		this.handicapTime = handicapTime;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
