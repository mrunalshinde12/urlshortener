package com.ms.controller;

import com.ms.model.Url;
import com.ms.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class UrlController {
    
    @Autowired
    private UrlService service;
    
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "{\"status\":\"OK\", \"message\":\"API works!\"}";
    }
    
    @PostMapping("/api/shorten")
    @ResponseBody
    public ResponseEntity<Url> shorten(@RequestBody Url request) {
        System.out.println(" Received URL: " + request.getOriginalUrl()); 
        Url result = service.shortenUrl(request.getOriginalUrl());
        System.out.println(" SAVED: " + result.getShortCode());          
        return ResponseEntity.ok(result);
    }
    
    
    @GetMapping("/s/{code}")
    public void redirect(@PathVariable("code") String shortCode, 
                        HttpServletResponse response) throws IOException {
        
        System.out.println("Looking up shortCode: " + shortCode);
        
        Url url = service.getOriginalUrl(shortCode);
        if (url != null) {
            System.out.println("Redirecting to: " + url.getOriginalUrl());
            response.sendRedirect(url.getOriginalUrl());
        } else {
            System.out.println("Not found: " + shortCode);
            response.setStatus(404);
            response.getWriter().write("Short URL not found: " + shortCode);
        }
    }
    
    @GetMapping("/stats")
    @ResponseBody
    public List<Map<String, String>> stats(){
    	List<Url> urls = service.getStats();
    	List<Map<String, String>> result = new ArrayList<>();
    	for(Url u : urls) {
    		Map<String, String> dto = new HashMap<>();
    		
    		dto.put("originalUrl", u.getOriginalUrl());
    		dto.put("shortCode", u.getShortCode());
    		
    		result.add(dto);
    	}
    	return result;
    }
    
}