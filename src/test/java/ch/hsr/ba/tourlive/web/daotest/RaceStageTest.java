package ch.hsr.ba.tourlive.web.daotest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ch.hsr.ba.tourlive.AbstractTestClass;
import ch.hsr.ba.tourlive.web.model.LiveTickerItem;
import ch.hsr.ba.tourlive.web.model.Race;
import ch.hsr.ba.tourlive.web.model.Stage;
import ch.hsr.ba.tourlive.web.model.enums.StageType;
import ch.hsr.ba.tourlive.web.service.DeviceService;
import ch.hsr.ba.tourlive.web.service.LiveTickerItemService;
import ch.hsr.ba.tourlive.web.service.RaceService;
import ch.hsr.ba.tourlive.web.service.StageService;

public class RaceStageTest extends AbstractTestClass {
	@Autowired
	private RaceService raceService;
	@Autowired
	private StageService stageService;
	@Autowired
	private LiveTickerItemService ltiService;
	@Autowired
	private DeviceService deviceService;
	private Race r;
	private Stage s;
	private LiveTickerItem l;

	@Before
	public void setup() {
		r = new Race();
		r.setRaceDescription("Junit Race Test Description");
		r.setRaceName("Junit Race Test Name");
		r.setRaceSlug("junit-test-race-slug");
		r.setVisible(false);
		r.setYear(2013);
		r.setRaceId(raceService.save(r));

		s = new Stage();
		s.setAdCode("lulu");
		s.setBannerImage("banner");
		s.setCompleted(true);
		s.setDistance(12.5F);
		s.setEndtime(1369495260000L);
		s.setStarttime(1369495240000L);
		s.setStageDescription("the description");
		s.setStageName("Junit Stage Name");
		s.setStageProfileImage("profilimage");
		s.setStageSlug("junit-stage-slug");
		s.setStagetype(StageType.FLATSTAGE);
		s.setVisible(true);
		s.setStageId(stageService.save(s));
		l = new LiveTickerItem();
	}

	@Test
	public void testRace() {
		r = raceService.getRaceById(r.getRaceId());
		Assert.assertNotNull(r);
	}

	@Test
	public void testStage() {
		Stage stage = stageService.getStageById(s.getStageId());
		stage.setRace(r);
		Assert.assertEquals("Stagename", "Junit Stage Name", stage.getStageName());
	}

	@After
	public void cleanUp() {
		System.out.println("________deleting " + s.getStageId());
		stageService.delete(s.getStageId());
		raceService.delete(r.getRaceId());
	}
}
