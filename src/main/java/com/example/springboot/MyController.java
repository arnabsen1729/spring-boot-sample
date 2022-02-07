package com.example.springboot;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MyController {

  private final Word cachedWord;

  MyController(){
    cachedWord = new Word();
  }

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @GetMapping("/word")
  public String getWord() {
    return cachedWord.getValue();
  }

  @PostMapping(
          value = "/word",
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Word> postWord(@RequestBody Word request) {
    cachedWord.setValue(request.getValue());
    return ResponseEntity.created(URI.create("/word")).body(cachedWord);
  }
}