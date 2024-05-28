package com.linkedupapp.linkedup.controller;

import com.linkedupapp.linkedup.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
public class LinkedInController {

    @Autowired
    private MeetupService meetupService;

    @GetMapping("/profile")
    public Map<String, Object> getProfile(OAuth2AuthenticationToken authentication) {
        String accessToken = authentication.getPrincipal().getAttributes().get("accessToken").toString();
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.linkedin.com/v2/me";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }

    @GetMapping("/events")
    public List<Map<String, Object>> getEvents(OAuth2AuthenticationToken authentication) {
        String profession = authentication.getPrincipal().getAttributes().get("headline").toString();
        String location = "Seattle"; // This should be dynamically obtained
        return meetupService.findEvents(location, profession);
    }
}
