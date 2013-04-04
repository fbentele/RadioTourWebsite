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
@Table(name = "RaceSituation")
public class RaceSituation {
	@Id
	@GeneratedValue
	private Long RaceSituationId;
	@Column
	private Long timestamp;

	// @Column
	// private List<String> situation;
	// @ManyToOne
	// @JoinColumn(name = "stageId")
	// private Stage stage;

	public class Situation {
		private int[] drivernumber;
		private boolean isLeader;
		private int groupnr;
		private Long handicaptime;
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
	}

	public Long getRaceSituationId() {
		return RaceSituationId;
	}

	public void setRaceSituationId(Long raceSituationId) {
		RaceSituationId = raceSituationId;
	}

	// public Stage getStage() {
	// return stage;
	// }
	//
	// public void setStage(Stage stage) {
	// this.stage = stage;
	// }

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	// public List<String> getSituation() {
	// return situation;
	// }
	//
	// public void setSituation(List<String> situation) {
	// this.situation = situation;
	// }
	//
	// public void addSituation(Situation situation) {
	// this.situation.add(situation);
	// }
}