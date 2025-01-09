package com.universityapp.users.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.universityapp.auth.dtos.internal.UserDTO;
import com.universityapp.auth.dtos.request.SignUpRequestDTO;
import com.universityapp.auth.services.IUserAuthService;
import com.universityapp.common.dtos.FindByCriteriaDTO;
import com.universityapp.common.enums.FindByCriteriaType;
import com.universityapp.users.dtos.internal.CreateUserDTO;
import com.universityapp.users.dtos.internal.UpdateUserDTO;
import com.universityapp.users.mappers.UserMapper;
import com.universityapp.users.repositories.IUserRepository;
import com.universityapp.users.repositories.impl.UserField;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService implements IUserAuthService {
    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        var user = this.userRepository
                .findUserByCriteria(
                        List.of(FindByCriteriaDTO.<UserField>builder().criterion(UserField.EMAIL).value(email).build()),
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

    @Override
    @Transactional
    public UserDTO createUser(SignUpRequestDTO requestDTO) {
        CreateUserDTO dto = this.userMapper.toCreateUserDTO(requestDTO);
        System.out.println(dto.getRole());
        dto.setUserId(UUID.randomUUID());
        dto.setIsActive(false);
        this.userRepository.createUser(dto);
        return new UserDTO(
                dto.getUserId(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getRole(),
                dto.getIsActive(),
                null);
    }

    @Override
    @Transactional
    public void updateCurrentRefreshToken(UUID userId, String newToken) {
        UpdateUserDTO dto = UpdateUserDTO.builder().currentRefreshToken(newToken).build();
        this.userRepository.updateUser(userId, dto);
    }

    @Override
    public Optional<UserDTO> findUserByPhoneNumber(String phoneNumber) {
        var user = this.userRepository
                .findUserByCriteria(
                        List.of(FindByCriteriaDTO.<UserField>builder().criterion(UserField.PHONE_NUMBER)
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
