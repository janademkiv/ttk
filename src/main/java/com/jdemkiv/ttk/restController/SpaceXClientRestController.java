package com.jdemkiv.ttk.restController;

import com.jdemkiv.ttk.dto.LaunchesDto;
import com.jdemkiv.ttk.dto.RocketDto;
import com.jdemkiv.ttk.service.SpaceXClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SpaceXClientRestController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SpaceXClientServiceImpl spaceXClientService;

    @RequestMapping(value = "/template/rocketsId")
    public List<Integer> getRocketsId() {
        return spaceXClientService.getRocketId();
    }

    //добавить айдишник
    //Получить по rocketId все возможные Launches.
    //я получу все лаунчи, из этих лаунчей отфильтрую с нужным мне рокет айди в сервисе. выходит каждый раз получаю жирную джейсонину и в ней капашусь
    @RequestMapping(value = "/template/launches/{*}")
    public LaunchesDto getLaunches(@PathVariable("*") int idLaunches) {
        return spaceXClientService.getLaunchesById(idLaunches);
    }



}
