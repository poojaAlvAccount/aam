package com.aam.userdetaiailextn.controller;

import com.aam.userdetaiailextn.json.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserDetailsController {

    @GetMapping(path = "/Adviser/AdviserProfile")
    public ResponseEntity<User> getUserDetailExtn(@PathVariable String emailId){
        User user = getUserDetail(emailId);

        if(ObjectUtils.isEmpty(user)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public User getUserDetail(String emailId){
        if(emailId.equals(emailId)){
            return new User();
        }
        return null;
    }
}
