package com.universityapp.users.repositories;



import java.util.Optional;

import com.universityapp.common.entities.User;


public interface UserRepository {
    User createUser(User dto);

    User updateUser(User dto);


    Optional<User> findUserByEmail(String email);

}
