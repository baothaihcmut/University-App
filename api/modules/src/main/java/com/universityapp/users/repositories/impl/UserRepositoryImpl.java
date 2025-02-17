package com.universityapp.users.repositories.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.universityapp.common.entities.User;


import com.universityapp.users.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

   
    public User createUser(User user) {
        this.entityManager.persist(user);
        return user;
    }


    public User updateUser(User user) {
        return this.entityManager.merge(user);
    }

    public Optional<User> findUserByEmail(String email){
        String query = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> jquery = this.entityManager.createQuery(query,User.class);
        jquery.setParameter("email", email);
        List<User> res = jquery.getResultList();
        return res.isEmpty() ? Optional.empty(): Optional.of(res.get(0));
    }
   

  
}
