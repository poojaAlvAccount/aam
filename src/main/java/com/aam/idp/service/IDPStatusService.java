package com.aam.idp.service;

import com.aam.idp.json.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class IDPStatusService {

    private final RestTemplate restTemplate;

    @Autowired
    public IDPStatusService(RestTemplate restTemplate) throws JsonProcessingException {
        this.restTemplate = restTemplate;
    }

    public String getUserStatus(String emailId) {
        String idp = null;
        String apiUrl = "http://.../AdviserPermissions/AgencyReferences"+emailId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl,String.class);
        try{
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                String responseBody = responseEntity.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(String.valueOf(responseBody));
                String userType = jsonNode.get("UserType").asText();
                if (emailId.toLowerCase().endsWith("lvfs.net")) {
                    idp = "AD";
                } else {
                    if (userType.equalsIgnoreCase("SOA")) {
                        idp = "SOA";
                        return idp;
                    } else if (userType.equalsIgnoreCase("AD")) {
                        idp = "AD";
                        return idp;
                    }else {
                        log.error("user Doesn't Exist");
                        Response response = new Response();
                        response.setStatus(409);
                        response.setMessage("user Doesn't Exist");
                    }
                }
                return idp;
            }
        }catch (Exception e){
        log.error("Exception occured while getting adviser profile details {}", e.getMessage());
        Response errorResponse = new Response();
        errorResponse.setMessage("Service error:" + e.getMessage());
        }
        return idp;
    }

    public String identifyMigrationStatus(String email) {
        return "Migrated to B2C";
    }
}
