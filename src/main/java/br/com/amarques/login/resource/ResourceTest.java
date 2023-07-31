package br.com.amarques.login.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test authentication")
@RequestMapping("/api/test")
public class ResourceTest {

    @GetMapping
    @Operation(summary = "Request for testing authentication")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You are in");
    }
}
