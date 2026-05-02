# URL Shortener

A simple Spring Boot application to shorten URLs.

## Features
- Shorten long URLs
- Redirect using short codes
- View recent shortened links

## Tech Stack
- Java
- Spring Boot
- HTML, CSS, JavaScript

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/mrunalshinde12/urlshortener.git

2. Navigate into the project folder:
    ```bash
     cd urlshortener

4. Run the application using Maven:
    ```bash
     mvn spring-boot:run

## Configuration
Before running, set the following environment variables:
- `DB_USERNAME` : your database username
- `DB_PASSWORD` : your database password

## Usage
POST /api/shorten
Request:
{
  "url": "https://example.com"
}

Response:
{
  "shortUrl": "http://localhost:9090/s/abc123"
}

## Screenshots
### Homepage
![Homepage]()

### Shortened URL Result
![Result](screenshots/result.png)
