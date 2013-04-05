package ch.hsr.ba.tourlive.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Situation")
public class Situation {
	@Id
	@Column
	@GeneratedValue
	private Long situationId;

	@ManyToOne
	@JoinColumn(name = "raceSituationId")
	private RaceSituation raceSituation;

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

	public int[] getDrivernumber() {
		return drivernumber;
	}

	public void setDrivernumber(int[] drivernumber) {
		this.drivernumber = drivernumber;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public int getGroupnr() {
		return groupnr;
	}

	public void setGroupnr(int groupnr) {
		this.groupnr = groupnr;
	}

	public Long getHandicaptime() {
		return handicaptime;
	}

	public void setHandicaptime(Long handicaptime) {
		this.handicaptime = handicaptime;
	}

	public boolean isField() {
		return isField;
	}

	public void setField(boolean isField) {
		this.isField = isField;
	}

	public Long getSituationId() {
		return situationId;
	}

	public void setSituationId(Long situationId) {
		this.situationId = situationId;
	}

	public RaceSituation getRaceSituation() {
		return raceSituation;
	}

	public void setRaceSituation(RaceSituation raceSituation) {
		this.raceSituation = raceSituation;
	}

	@Override
	public String toString() {
		return "Situation [situationId=" + situationId + ", raceSituation="
				+ raceSituation + ", drivernumber="
				+ Arrays.toString(drivernumber) + ", isLeader=" + isLeader
				+ ", groupnr=" + groupnr + ", handicaptime=" + handicaptime
				+ ", isField=" + isField + "]";
	}

}