package com.software.project.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String name);
    public User findByRoles(String roles);
    public User findByEmail(String email);
    
    
}
