package com.javasession.controller;

import com.javasession.dto.UserResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v2")
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/usr")
    public List<String> getUsers() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<UserResponse[]> response = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/users",
                HttpMethod.GET,
                new HttpEntity<String>(httpHeaders),
                UserResponse[].class);

        UserResponse[] body = response.getBody();

        System.out.println("Arrays.toString(body) = " + Arrays.toString(body));

        List<String> result = new ArrayList<>();
        for (UserResponse usrResponse : Objects.requireNonNull(body)) {
            result.add(usrResponse.getName().toUpperCase());
        }
        return result;
    }

}
