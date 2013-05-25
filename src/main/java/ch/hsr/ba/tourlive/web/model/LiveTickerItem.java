package ch.hsr.ba.tourlive.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.ba.tourlive.web.utils.DateUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "LiveTickerItem")
public class LiveTickerItem {

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
		return DateUtil.toTimeFormat(timestamp);
	}

	public void setTimestampAsTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = DateUtil.timeToTimestamp(timestamp);
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
