package ch.hsr.ba.tourlive.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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
@Table(name = "Stagedata")
public class StageData implements Serializable {
	private static final long serialVersionUID = -6609689730044394749L;

	@Id
	@GeneratedValue
	@Column(name = "STAGEDATA_ID")
	private Long stagedataid;

	@Column
	private Time stagetime;

	@Column
	private int stagealtitude;

	@Column
	private int stagedistance;

	@Column
	private float averagespeed;

	@Column
	private Time mobiletime;

	@Column
	private Date mobile;

	public Long getStagedataid() {
		return stagedataid;
	}

	public void setStagedataid(Long stagedataid) {
		this.stagedataid = stagedataid;
	}

	public Time getStagetime() {
		return stagetime;
	}

	public void setStagetime(Time stagetime) {
		this.stagetime = stagetime;
	}

	public int getStagealtitude() {
		return stagealtitude;
	}

	public void setStagealtitude(int stagealtitude) {
		this.stagealtitude = stagealtitude;
	}

	public int getStagedistance() {
		return stagedistance;
	}

	public void setStagedistance(int stagedistance) {
		this.stagedistance = stagedistance;
	}

	public float getAveragespeed() {
		return averagespeed;
	}

	public void setAveragespeed(float averagespeed) {
		this.averagespeed = averagespeed;
	}

	public Time getMobiletime() {
		return mobiletime;
	}

	public void setMobiletime(Time mobiletime) {
		this.mobiletime = mobiletime;
	}

	public Date getMobile() {
		return mobile;
	}

	public void setMobile(Date mobile) {
		this.mobile = mobile;
	}

}
