package ch.hsr.ba.tourlive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "DeviceData")
public class DeviceData {
	@Id
	@GeneratedValue
	@Column(name = "DEVICEDATA_ID")
	private Long devicedataid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VALUECONTAINER_ID")
	private ValueContainer valueContainer;

	@OneToOne
	@JoinColumn(name = "DEVICE_ID")
	private Device device;
}
