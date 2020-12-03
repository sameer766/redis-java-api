package com.sameer.redis.repo;

import com.sameer.redis.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
   Optional<User> findById(String id);

}


//this impl was when redis template was not supported with CRUDRepository interface

/*
  private RedisTemplate<String, User> redisTemplate;
  private HashOperations hashOperations;

  public UserRepo() {

  }
  public UserRepo(RedisTemplate<String, User> redisTemplate,
                  HashOperations hashOperations) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
  }


  public void save(User user) {
    hashOperations.put("USER", user.getId(), user);
  }

  public void update(User user) {
    save(user);
  }

  public void delete(String id) {
    hashOperations.delete("USER", id);
  }

  public Map<String, User> findAll() {
    return hashOperations.entries("USER");
  }

  public User findById(String id) {
    return (User) hashOperations.get("USER", id);
  }


 */