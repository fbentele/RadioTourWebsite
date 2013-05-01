package ch.hsr.ba.tourlive.model.rider;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.model.Stage;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "RaceSituation")
public class RaceSituation {
	@Id
	@GeneratedValue
	@Column
	private Long raceSituationId;
	@Column
	private Long timestamp;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "stageId")
	private Stage stage;

	@Column(columnDefinition = "longblob")
	private Situation[] situation;

	public RaceSituation() {

	}

	public RaceSituation(Long timestamp, Stage stage, Situation situation) {
		this.timestamp = timestamp;
	}

	public RaceSituation(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getRaceSituationId() {
		return raceSituationId;
	}

	public void setRaceSituationId(Long raceSituationId) {
		this.raceSituationId = raceSituationId;
	}

	public Long getTimestampAsLong() {
		return timestamp;
	}

	public String getTimestamp() {
		SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
		try {
			Date d = new Date(this.timestamp);
			return date.format(d);
		} catch (NullPointerException e) {
			return "unbekannt";
		}
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Situation[] getSituation() {
		return situation;
	}

	public void setSituation(Situation[] situation) {
		this.situation = situation;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "RaceSituation [RaceSituationId=" + raceSituationId + ", timestamp=" + timestamp
				+ "]";
	}

}