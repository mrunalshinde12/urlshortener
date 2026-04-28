URL Shortener

A simple Spring Boot application to shorten URLs.

Features

Shorten long URLs into compact links

Redirect seamlessly using short codes

View and manage recent shortened links

Copy-to-clipboard functionality for quick sharing

Tech Stack

Backend: Java, Spring Boot

Frontend: HTML, CSS, JavaScript

Build Tool: Maven

Installation

Clone the repository:

git clone https://github.com/mrunalshinde12/urlshortener.git

Navigate into the project folder:

cd urlshortener

Run the application using Maven:

mvn spring-boot:run

Usage

Start the application.

Use the /api/shorten endpoint to shorten URLs.

Example request:

{ "url": "https://example.com" }

Example response:

{ "shortUrl": "http://localhost:8080/r/abc123" }

Access shortened links via /r/{code}.

Screenshots

(Add screenshots of your UI here to showcase the application visually)

Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Future Improvements

Add analytics for link usage

Implement user authentication

Provide custom aliases for shortened URLs
