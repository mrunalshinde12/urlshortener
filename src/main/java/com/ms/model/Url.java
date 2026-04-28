package com.ms.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "urls")
@Data
public class Url {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String originalUrl;
	private String shortCode;
	private int clickCount=0;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	
}
