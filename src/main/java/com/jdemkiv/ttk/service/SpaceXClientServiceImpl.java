package com.jdemkiv.ttk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdemkiv.ttk.dto.LaunchesDto;
import com.jdemkiv.ttk.dto.RocketDto;
import com.jdemkiv.ttk.entities.ResponseRequestEntity;
import com.jdemkiv.ttk.exceptions.ResourceNotFoundException;
import com.jdemkiv.ttk.repositories.ResponseRequestRepository;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpaceXClientServiceImpl implements SpaceXClientService {
    private final ResponseRequestRepository responseRequestRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final String rocketUrl;
    private final String launchUrl;

    public SpaceXClientServiceImpl(
            ResponseRequestRepository responseRequestRepository, ObjectMapper objectMapper, RestTemplate restTemplate,
            @Value("${spacex.rocket.uri}") String rocketUrl,
            @Value("${spacex.launches.uri}") String launchUrl) {
        this.responseRequestRepository = responseRequestRepository;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        this.rocketUrl = rocketUrl;
        this.launchUrl = launchUrl;
    }

    @Override
    public List<Integer> getRocketId() throws JsonProcessingException {
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

        ResponseRequestEntity responseRequestEntity = new ResponseRequestEntity();
        responseRequestEntity.setRequest(rocketUrl);
        responseRequestEntity.setResponse(objectMapper.writeValueAsString(list));

        return list.stream().map(RocketDto::getId).collect(Collectors.toList());
    }

    @Override
    public List<LaunchesDto> getAllLaunchesById(String idLaunches) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<LaunchesDto> launchesDto = restTemplate.exchange(
                launchUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<LaunchesDto>>() {
                }
        ).getBody();

        if (launchesDto == null) {
            throw new ResourceNotFoundException("Not found launches by id: " + idLaunches);
        }

        List<LaunchesDto> listLaunchesDtoFilter = launchesDto.stream().filter(x -> Objects.equals(x.getRocketLaunchDto().getRocketId(), idLaunches)).collect(Collectors.toList());

        ResponseRequestEntity responseRequestEntity = new ResponseRequestEntity();
        responseRequestEntity.setRequest(launchUrl + idLaunches);
        responseRequestEntity.setResponse(objectMapper.writeValueAsString(listLaunchesDtoFilter));
        responseRequestRepository.save(responseRequestEntity);

        return listLaunchesDtoFilter;
    }


}
