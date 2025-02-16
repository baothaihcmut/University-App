package com.universityapp.users.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.universityapp.auth.dtos.internal.UserDTO;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.common.filters.FilterDTO;
import com.universityapp.users.repositories.IUserRepository;
import com.universityapp.users.repositories.impl.UserField;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;

    public void test() {
    }

    public Optional<UserDTO> findUserByPhoneNumber(String phoneNumber) {
        var user = this.userRepository
                .findUserByCriteria(
                        List.of(FilterDTO.<UserField>builder().criterion(UserField.PHONE_NUMBER)
                                .value(phoneNumber).build()),
                        FindByCriteriaType.AND);

        if (user.size() > 0) {
            var userRes = user.getFirst();
            return Optional.of(
                    new UserDTO(
                            userRes.getUserId(),
                            userRes.getEmail(),
                            userRes.getPassword(),
                            userRes.getRole(),
                            userRes.getIsActive(),
                            userRes.getCurrentRefreshToken()));
        } else {
            return Optional.empty();
        }
    }

}
