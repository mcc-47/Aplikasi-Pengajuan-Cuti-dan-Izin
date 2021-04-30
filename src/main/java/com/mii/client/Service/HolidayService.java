/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Models.Holiday;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Fadel
 */
@Service
public class HolidayService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}/api/holiday")
    private String url;
    
    public List<Holiday> getAll() {
        ResponseEntity<List<Holiday>> response = restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<Holiday>>() {
                });

        return response.getBody();
    }
     
    public Holiday getById(Integer id) {
        return restTemplate.getForEntity(url + "/" + id, Holiday.class).getBody();
    }

    public Holiday create(Holiday holiday) {
        HttpEntity entity = new HttpEntity(holiday, RequestFormat.createHeaders());
        ResponseEntity<Holiday> res = restTemplate.exchange(url, HttpMethod.POST, entity, Holiday.class);

        return res.getBody();
    }

    public Holiday updateById(Integer id, Holiday holiday) {
        HttpEntity entity = new HttpEntity(holiday, RequestFormat.createHeaders());
        ResponseEntity<Holiday> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, Holiday.class);
        
        return res.getBody();
    }

    public Holiday delete(Integer id) {
        ResponseEntity<Holiday> res = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, Holiday.class);
        return res.getBody();
    }
}
