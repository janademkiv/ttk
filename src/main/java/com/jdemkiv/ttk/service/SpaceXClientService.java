package com.jdemkiv.ttk.service;

import com.jdemkiv.ttk.dto.LaunchesDto;
import com.jdemkiv.ttk.dto.RocketDto;

import java.util.List;

public interface SpaceXClientService {


    List<Integer> getRocketId();

    LaunchesDto getLaunchesById(int idLaunches);
}
