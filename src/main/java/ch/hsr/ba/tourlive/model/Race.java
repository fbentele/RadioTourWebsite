package ch.hsr.ba.tourlive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Race")
public class Race {
	@Id
	@Column
	@GeneratedValue
	private Long raceId;

	@NotEmpty
	@Column
	private String raceName;

	/*
	 * A slug is a pathpart in an url
	 */
	@NotEmpty
	@Column(unique = true)
	private String raceSlug;

	@Column
	private String raceDescription;

	@Min(value = 1999)
	@Max(value = 2100)
	@Column
	private int year;

	@Column
	private Boolean visible;

	public Long getRaceId() {
		return raceId;
	}

	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getRaceSlug() {
		return raceSlug;
	}

	public void setRaceSlug(String raceSlug) {
		this.raceSlug = raceSlug;
	}

	public String getRaceDescription() {
		return raceDescription;
	}

	public void setRaceDescription(String raceDescription) {
		this.raceDescription = raceDescription;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Race [raceId=" + raceId + ", raceName=" + raceName + ", raceSlug=" + raceSlug
				+ ", raceDescription=" + raceDescription + ", year=" + year + "]";
	}

}
