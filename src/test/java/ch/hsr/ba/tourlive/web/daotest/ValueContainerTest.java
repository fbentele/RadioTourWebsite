package ch.hsr.ba.tourlive.web.daotest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import ch.hsr.ba.tourlive.AbstractTestClass;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;

public class ValueContainerTest extends AbstractTestClass {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ValueContainerService valueContainerService;

	@Before
	public void setup() {
	}

	@Test
	public void testValueContainer() {
		Device d = new Device("thetestdevice");
		d.setColor("#333");
		d.setLabelName("Front");
		d.setPhoneNr("0788888888");
		deviceService.save(d);
		Assert.notNull(deviceService.getDeviceById("thetestdevice"));
		deviceService.delete("thetestdevice");
	}

}
