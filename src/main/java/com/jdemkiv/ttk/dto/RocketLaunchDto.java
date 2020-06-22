package com.jdemkiv.ttk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RocketLaunchDto {
    @JsonProperty("rocket_id")
    String rocketId;
}
