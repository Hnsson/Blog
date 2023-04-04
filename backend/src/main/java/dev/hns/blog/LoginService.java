package dev.hns.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
            return "User exists";
        } else {
            return "User does not exist";
        }
    }

    public User createUser() {
        User user = repository.insert(new User("", ""));
        return user;
    }
}
