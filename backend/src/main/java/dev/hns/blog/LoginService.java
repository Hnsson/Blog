package dev.hns.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;


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
            long tokenExpirationMillis = 3600000;
            Date expiration = new Date(new Date().getTime() + tokenExpirationMillis);

            return JwtAuthentication.createToken(user.getUsername(), expiration);
        } else {
            return null;
        }
    }

    public User createUser() {
        User user = repository.insert(new User("", ""));
        return user;
    }
}
