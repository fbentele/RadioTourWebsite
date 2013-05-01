package ch.hsr.ba.tourlive.model;

import java.io.Serializable;

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
@Table(name = "Situation")
public class Situation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5090788260118136922L;

	@Id
	@Column
	@GeneratedValue
	private Long situationId;

	@Column
	private int[] drivernumber;
	@Column
	private boolean isLeader;
	@Column
	private int groupnr;
	@Column
	private Long handicaptime;
	@Column
	private boolean isField;

	public Situation() {

	}

	public Situation(Long situationId, int[] drivernumber, boolean isLeader, int groupnr,
			Long handicaptime, boolean isField) {
		super();
		this.situationId = situationId;
		this.drivernumber = drivernumber;
		this.isLeader = isLeader;
		this.groupnr = groupnr;
		this.handicaptime = handicaptime;
		this.isField = isField;
	}

	public int[] getDrivernumber() {
		return drivernumber;
	}

	public void setDrivernumber(int[] drivernumber) {
		this.drivernumber = drivernumber;
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
		int i = 0;
		try {
			i = drivernumber.length;
		} catch (Exception e) {

		}
		if (i < 1)
			return "none";
		if (i == 1)
			return "one";
		if (i == 2)
			return "two";
		if (i < 5)
			return "small";
		if (i < 10)
			return "medium";
		if (i >= 10)
			return "large";
		return "none";
	}

	public Long getHandicaptime() {
		return handicaptime;
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

	public Long getSituationId() {
		return situationId;
	}

	public void setSituationId(Long situationId) {
		this.situationId = situationId;
	}

	// public RaceSituation getRaceSituation() {
	// return raceSituation;
	// }
	//
	// public void setRaceSituation(RaceSituation raceSituation) {
	// this.raceSituation = raceSituation;
	// }
	//
	// @Override
	// public String toString() {
	// return "Situation [situationId=" + situationId + ", raceSituation=" +
	// raceSituation
	// + ", drivernumber=" + Arrays.toString(drivernumber) + ", isLeader=" +
	// isLeader
	// + ", groupnr=" + groupnr + ", handicaptime=" + handicaptime +
	// ", isField="
	// + isField + "]";
	// }

}