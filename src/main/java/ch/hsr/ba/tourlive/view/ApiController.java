package ch.hsr.ba.tourlive.view;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hsr.ba.tourlive.model.PositionData;
import ch.hsr.ba.tourlive.model.ValueContainer;
import ch.hsr.ba.tourlive.service.PositionDataService;
import ch.hsr.ba.tourlive.service.ValueContainerService;

@Controller
public class ApiController {
	@Autowired
	ApplicationContext context;
	@Autowired
	PositionDataService positionDataService;
	@Autowired
	ValueContainerService valueContainerService;

	// test json
	// {"altitude":0.0,"timestamp":1363958381172,"latitude":47.2234989,"longitude":8.817247,"speed":0.0,"direction":0.0}
	// {"timestamp":17529112,"speed":42.5,"direction":5,"incline":9,"longitude":31.111,"latitude":43.9999,"altitude":50}
	// {"timestamp":11929112,"speed":12.5,"direction":5,"incline":9,"longitude":31.111,"latitude":43.9999,"altitude":50}

	@RequestMapping(value = "/api/positiondata", method = RequestMethod.POST)
	@ResponseBody
	public void api(@RequestBody final PositionData request) {
		positionDataService.save(request);
	}

	@RequestMapping(value = "/api/valuecontainer", method = RequestMethod.POST)
	@ResponseBody
	public void values(@RequestBody final ValueContainer request) {
		valueContainerService.save(request);
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String showdata(Locale locale, Model model) {
		model.addAttribute("positions", positionDataService.getAll());
		model.addAttribute(
				"current",
				positionDataService.getAll().get(
						positionDataService.getAll().size() - 1));
		return "api";
	}
}


//{
//    "valueconatiner": {
//        "deviceid": "asdf1234",
//        "username": "hellouser",
//        "timestamp": 112223334,
//        "positiondata": {
//            "longitude": 31.111,
//            "latitude": 43.9999,
//            "speed": 12.5,
//            "altitude": 50,
//            "direction": 5,
//            "incline": 9,
//            "satellites": "2/11",
//            "gpssendinterval": 5
//        },
//        "stagedata": {
//            "stagetime": "00:00:00",
//            "stagealtitude": 10,
//            "distance": 12.5,
//            "averagespeed": 24.6
//        },
//        "netdata": {
//            "cellnumber": 65432,
//            "locationarea": 909,
//            "signalstrength": 96,
//            "mncmcc": "asdf",
//            "upspeed": 10,
//            "rtt": 55,
//            "packetloss": 10,
//            "technology": "umts"
//        }
//    }
//}