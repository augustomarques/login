package br.com.amarques.login.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class ResourceTest {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You are in");
    }
}
