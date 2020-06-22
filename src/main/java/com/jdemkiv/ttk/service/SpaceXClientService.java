package com.jdemkiv.ttk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jdemkiv.ttk.dto.LaunchesDto;

import java.util.List;

public interface SpaceXClientService {

    List<Integer> getRocketId() throws JsonProcessingException;

    List<LaunchesDto> getAllLaunchesById(String idLaunches) throws JsonProcessingException;
}
