package com.jdemkiv.ttk.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdemkiv.ttk.dto.LaunchesDto;
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
    public List<Integer> getRocketsId() throws JsonProcessingException {
        return spaceXClientService.getRocketId();
    }

    @RequestMapping(value = "/template/launches/{*}")
    public List<LaunchesDto> getLaunches(@PathVariable("*") String idLaunches) throws JsonProcessingException {
        return spaceXClientService.getAllLaunchesById(idLaunches);
    }
}
