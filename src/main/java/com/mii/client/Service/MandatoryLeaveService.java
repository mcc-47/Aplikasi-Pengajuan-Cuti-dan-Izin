/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Models.MandatoryLeave;
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
 * @author jakab
 */
@Service
public class MandatoryLeaveService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}/api/mandatory-leave")
    private String url;
    
     public List<MandatoryLeave> getAll() {
        ResponseEntity<List<MandatoryLeave>> response = restTemplate
                .exchange(url, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<List<MandatoryLeave>>() {
                });

        return response.getBody();
    }
     
    public MandatoryLeave getById(Integer id) {
        return restTemplate.getForEntity(url + "/" + id, MandatoryLeave.class).getBody();
    }

    public MandatoryLeave create(MandatoryLeave mandatoryLeave) {
        HttpEntity entity = new HttpEntity(mandatoryLeave, RequestFormat.createHeaders());
        ResponseEntity<MandatoryLeave> res = restTemplate.exchange(url, HttpMethod.POST, entity, MandatoryLeave.class);

        return res.getBody();
    }

    public MandatoryLeave updateById(Integer id, MandatoryLeave mandatoryLeave) {
        HttpEntity entity = new HttpEntity(mandatoryLeave, RequestFormat.createHeaders());
        ResponseEntity<MandatoryLeave> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, MandatoryLeave.class);
        
        return res.getBody();
    }

    public MandatoryLeave delete(Integer id) {
        ResponseEntity<MandatoryLeave> res = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, MandatoryLeave.class);
        return res.getBody();
    }
}