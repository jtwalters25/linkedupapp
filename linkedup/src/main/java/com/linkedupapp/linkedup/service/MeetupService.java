package com.linkedupapp.linkedup.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MeetupService {

    private final String MEETUP_API_KEY = "YOUR_MEETUP_API_KEY";

    public List<Map<String, Object>> findEvents(String location, String profession) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.meetup.com/find/upcoming_events?key=" + MEETUP_API_KEY + "&text=" + profession + "&location=" + location;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("events");
    }
}
