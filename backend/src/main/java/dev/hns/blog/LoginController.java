package dev.hns.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody Map<String, String> payload) {
        // return new ResponseEntity<>("yo", HttpStatus.OK);
        return new ResponseEntity<>(service.loginAuthentication(payload.get("username"), payload.get("password")), HttpStatus.OK);
    }

    @GetMapping("/createpost/test")
    public void creatPost() {
        service.createUser();
    }

}
