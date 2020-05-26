package controller;

import app.generator.modol.Station;
import app.generator.response.RpcResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.station.service.StationService;

@RestController
@RequestMapping("/home")
public class HomePageController {
    @Reference
    public StationService stationService;

    @GetMapping("/search_station")
    public RpcResult searchStation(String category) {
        System.out.println(category);
        Station station = new Station();
        station.setCategory(category);
        return stationService.searchStation(station);
    }

    @GetMapping("/search/{key}")
    public RpcResult searchBlurry(@PathVariable String key) {
        return stationService.searchStationByBlurry(key);
    }
}
