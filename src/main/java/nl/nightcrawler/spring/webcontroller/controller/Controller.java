package nl.nightcrawler.spring.webcontroller.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

    @GetMapping("hello-world")
    public ResponseEntity<String> greeting(@RequestParam(required = false) boolean throwException) {
        if (!throwException) {
            return ResponseEntity.ok("Hello World");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You shall not pass!");
    }

    @PostMapping("hello-world")
    public ResponseEntity<String> greetingViaPost(@RequestParam(required = false) boolean throwException) {
        if (!throwException) {
            return ResponseEntity.ok("Hello World");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You shall not pass!");
    }

    @GetMapping("hello-world-1")
    public ResponseEntity<String> greeting1() {
        throw new IllegalStateException("You shall not pass!", new Throwable("You shall not pass!"));
    }
}
