package com.sameer.redis.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sameer.redis.model.User;
import com.sameer.redis.repo.UserRepo;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource<T> {
  @Autowired
  private UserRepo userRepo;

  @Autowired
  ObjectMapper objectMapper;

  @PostMapping("/add")
  public ResponseEntity<?> addUser(@RequestBody User user) {
    try {
      userRepo.save(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  @GetMapping("/get/all")
  public List<User> getUsers() {
    List<User> userList = new LinkedList<>();
    Iterable<User> users = userRepo.findAll();
    users.forEach(userList::add);
    return userList;
  }

  @GetMapping("/findById/{id}")
  public ResponseEntity<?> getUser(@PathVariable String id) throws JsonProcessingException {

    Optional<User> user = userRepo.findById(id);
    if (user.isPresent()) {
      return ResponseEntity.ok(objectMapper.writeValueAsString(user.get()));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody User user) {
    return addUser(user);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable String id) throws JsonProcessingException {
    Optional<User> user = userRepo.findById(id);
    if (user.isPresent()) {
      userRepo.delete(user.get());
      return ResponseEntity.status(HttpStatus.OK).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/clear")
  public void deleteAll() {
    userRepo.deleteAll();
  }

}
