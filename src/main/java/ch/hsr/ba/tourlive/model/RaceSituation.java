package ch.hsr.ba.tourlive.model;

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
@Table(name = "RaceSituation")
public class RaceSituation {
	@Id
	@GeneratedValue
	private Long raceSituationId;
	@Column
	private Long timestamp;

	@ManyToOne
	@JoinColumn(name = "stageId")
	private Stage stage;

	public Long getRaceSituationId() {
		return raceSituationId;
	}

	public void setRaceSituationId(Long raceSituationId) {
		this.raceSituationId = raceSituationId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "RaceSituation [RaceSituationId=" + raceSituationId
				+ ", timestamp=" + timestamp + "]";
	}

}