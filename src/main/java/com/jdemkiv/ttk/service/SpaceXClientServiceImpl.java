package com.jdemkiv.ttk.service;

import com.jdemkiv.ttk.dto.LaunchesDto;
import com.jdemkiv.ttk.dto.RocketDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Service
public class SpaceXClientServiceImpl implements SpaceXClientService {
    private final RestTemplate restTemplate;
    private final String rocketUrl;
    private final String launchUrl;

    public SpaceXClientServiceImpl(
            RestTemplate restTemplate,
            @Value("${spacex.rocket.uri}") String rocketUrl,
            @Value("${spacex.launches.uri}") String launchUrl) {
        this.restTemplate = restTemplate;
        this.rocketUrl = rocketUrl;
        this.launchUrl = launchUrl;
    }

    @Override
    public List<Integer> getRocketId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<RocketDto> list = restTemplate.exchange(
                rocketUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<RocketDto>>() {
                }
        ).getBody();

        return list.stream().map(RocketDto::getId).collect(Collectors.toList());
    }

    @Override
    public LaunchesDto getLaunchesById(int idLaunches) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                launchUrl + idLaunches,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<LaunchesDto>() {
                }
        ).getBody();
    }

}
