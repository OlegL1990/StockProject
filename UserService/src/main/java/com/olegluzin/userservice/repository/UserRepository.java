package com.olegluzin.userservice.repository;


import com.olegluzin.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
