package com.ms.service;

import com.ms.model.Url;
import com.ms.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	public Url shortenUrl(String originalUrl) {
	    System.out.println("Creating short URL for: " + originalUrl);  
	    
	    String shortCode = generateShortCode();
	    Url url = new Url();
	    url.setOriginalUrl(originalUrl);
	    url.setShortCode(shortCode);
	    
	    Url saved = repository.save(url);  
	    System.out.println("SAVED ID: " + saved.getId());             
	    return saved;
	
	}
	
	public Url getOriginalUrl(String shortCode) {
			Optional<Url> url = repository.findByShortCode(shortCode);
			if(url.isPresent()) {
				
				Url found = url.get();
				found.setClickCount(found.getClickCount()+1);
				repository.save(found);
				System.out.println("Clicks now:" +found.getClickCount());
				return found;
			}
			return null;
	}
	
	public List<Url>getStats(){
		return repository.findTop5ByOrderByIdDesc();
	}
	
	private String generateShortCode() {
		return UUID.randomUUID().toString().substring(0,6).toLowerCase();
	}

}
