package com.aam.idp.controller;

import com.aam.idp.json.Response;
import com.aam.idp.service.IDPStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IDPStatusController {

        @Autowired
        private IDPStatusService idpStatusService;

        @GetMapping("/Adviser/idpandmigrationstatus")
        public ResponseEntity<Response> identifyIDPAndMigrationStatus(@RequestParam String emailId) {
            String idp = idpStatusService.getUserStatus(emailId);
//            String migrationStatus = idpStatusService.identifyMigrationStatus(emailId);
            if(idp.equals("AD") || idp.equals("SOA")){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("AD/B2C/SOA", true, true, true));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("User doesn't Exists"));
            }

        }


}
