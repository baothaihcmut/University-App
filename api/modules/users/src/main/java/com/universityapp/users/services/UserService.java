package com.universityapp.users.services;

import org.springframework.stereotype.Service;

import com.universityapp.users.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public void test() {
        this.userRepository.findAllUser();
    }

}
