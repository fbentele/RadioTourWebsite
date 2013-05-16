package ch.hsr.ba.tourlive.web.model;

import java.text.ParseException;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "LiveTickerItem")
public class LiveTickerItem {

	private static final String TIME_FORMAT_PATTERN = "HH:mm:ss";

	@Id
	@Column
	@GeneratedValue
	private Long liveTickerId;
	@Column
	private Long timestamp;
	@Column
	private String news;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "stageId")
	private Stage stage;

	public LiveTickerItem() {

	}

	public LiveTickerItem(Long timestamp, String news, Stage stage) {
		super();
		this.timestamp = timestamp;
		this.news = news;
		this.stage = stage;
	}

	public Long getLiveTickerId() {
		return liveTickerId;
	}

	public void setLiveTickerId(Long liveTickerId) {
		this.liveTickerId = liveTickerId;
	}

	public Long getTimestampAsTimestamp() {
		return timestamp;
	}

	public String getTimestamp() {
		SimpleDateFormat date = new SimpleDateFormat(TIME_FORMAT_PATTERN);
		try {
			Date d = new Date(this.timestamp);
			return date.format(d);
		} catch (NullPointerException e) {
			return "Zeitpunkt unbekannt";
		}
	}

	public void setTimestampAsTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(String timestamp) {
		try {
			Date date = new SimpleDateFormat(TIME_FORMAT_PATTERN).parse(timestamp);
			this.timestamp = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
