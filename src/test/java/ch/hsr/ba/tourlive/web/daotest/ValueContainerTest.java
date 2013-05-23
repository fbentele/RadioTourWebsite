package ch.hsr.ba.tourlive.web.daotest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import ch.hsr.ba.tourlive.AbstractTestClass;
import ch.hsr.ba.tourlive.web.model.Device;
import ch.hsr.ba.tourlive.web.model.NetData;
import ch.hsr.ba.tourlive.web.model.PositionData;
import ch.hsr.ba.tourlive.web.model.StageData;
import ch.hsr.ba.tourlive.web.model.ValueContainer;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.NetDataService;
import ch.hsr.ba.tourlive.web.service.PositionDataService;
import ch.hsr.ba.tourlive.web.service.StageDataService;
import ch.hsr.ba.tourlive.web.service.ValueContainerService;

public class ValueContainerTest extends AbstractTestClass {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ValueContainerService valueContainerService;
	@Autowired
	private PositionDataService posService;
	@Autowired
	private StageDataService stageDataService;
	@Autowired
	private NetDataService netService;

	private Device d;
	private PositionData pos;
	private StageData sta;
	private NetData net;

	@Before
	public void setup() {
	}

	@Test
	public void testDevice() {
		d = new Device("thetestdevice");
		d.setColor("#333");
		d.setLabelName("Front");
		d.setPhoneNr("0788888888");
		deviceService.save(d);
		Assert.notNull(deviceService.getDeviceById("thetestdevice"));
		deviceService.delete("thetestdevice");
	}

	@Test
	public void testPositionData() {
		pos = new PositionData();
		pos.setAccuracy(15F);
		pos.setAltitude(11);
		pos.setLatitude(44.22F);
		pos.setLongitude(11.34F);
		pos.setIncline(15);
		pos.setSatellites("10/12");
		pos.setSpeed(34.8F);
		int size = posService.getAll().size();
		long id = posService.save(pos);
		assertEquals(size + 1, posService.getAll().size());
		posService.delete(id);
	}

	@Test
	public void testStageData() {
		sta = new StageData();
		sta.setAverageSpeed(28.9F);
		sta.setDistance(12.7F);
		sta.setStageTime("11:45:00");
		sta.setStageUpAltitude(812D);
		int size = stageDataService.getAll().size();
		long id = stageDataService.save(sta);
		assertEquals(size + 1, stageDataService.getAll().size());
		stageDataService.delete(id);
	}

	@Test
	public void testNetData() {
		net = new NetData();
		net.setCellNumber(16);
		net.setLocationArea(48);
		net.setNetDataID(16);
		int size = netService.getAll().size();
		Integer id = netService.save(net);
		net.setNetDataID(id);
		assertEquals(size + 1, netService.getAll().size());
	}

	@Test
	public void testValueContainer() {
		ValueContainer vc = new ValueContainer();
		vc.setDevice(d);
		vc.setNetData(net);
		vc.setPositionData(pos);
		vc.setStageData(sta);
		vc.setTimestamp(1311111111111L);
		int size = valueContainerService.getAllValueContainers().size();
		Long id = valueContainerService.save(vc);
		// assertEquals(size + 1,
		// valueContainerService.getAllValueContainers().size());
		valueContainerService.delete(id);
	}

	@Test
	public void testContainerDeletion() {

	}
}
