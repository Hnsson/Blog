package dev.hns.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String loginAuthentication(String username, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.addCriteria(Criteria.where("password").is(password));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            String secretString = "xM1R9cZe4MhL4jK3wS6nH8qU2bY5aG7tP0oFpDfVgJyKlN9i";
            byte[] decodedKey = Base64.getDecoder().decode(secretString);
            SecretKey secretKey = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
            
            long tokenExpirationMillis = 3600000;
            Date now = new Date();
            Date expiration = new Date(now.getTime() + tokenExpirationMillis);
            return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();

        } else {
            return null;
        }
    }

    public User createUser() {
        User user = repository.insert(new User("", ""));
        return user;
    }
}
