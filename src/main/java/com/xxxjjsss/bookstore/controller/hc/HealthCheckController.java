package com.xxxjjsss.bookstore.controller.hc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hc")
public class HealthCheckController {

    @Value("${server.env}")
    private String env;
    @Value("${server.port}")
    private String serverPort;
    @Value("${serverName}")
    private String serverName;

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("serverName", serverName);
        responseData.put("serverPort", serverPort);
        responseData.put("env", env);
        responseData.put("name", "bookstore-server");

        return ResponseEntity.ok(responseData);
    }


    @GetMapping("/env")
    public ResponseEntity<?> getEnv() {
        return ResponseEntity.ok(env);
    }

}
