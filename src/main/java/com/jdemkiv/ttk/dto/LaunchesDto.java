package com.jdemkiv.ttk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LaunchesDto {
    @JsonProperty("mission_name")
    String missionName;
    @JsonProperty("launch_year")
    String launchYear;
    LinksDto links;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "rocket")
    RocketLaunchDto rocketLaunchDto;
}
