package com.universityapp.modules.users.interactors.imdl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.universityapp.modules.auth.services.AuthService;
import com.universityapp.modules.users.entities.Dependent;
import com.universityapp.modules.users.entities.User;
import com.universityapp.modules.users.interactors.UserInteractor;
import com.universityapp.modules.users.mappers.UserMapper;
import com.universityapp.modules.users.presenters.input.DependentInput;
import com.universityapp.modules.users.repositories.DependentRespository;
import com.universityapp.modules.users.repositories.StudentRespository;
import com.universityapp.modules.users.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInteractorImdl implements UserInteractor {
     private final AuthService authService;
     private final DependentRespository dependentRespository;
     private final UserRepository userRepository;
     private final StudentRespository studentRepository;
     private final UserMapper userMapper;

     public List<Dependent> getDependents() {
          return dependentRespository.findAll();
     }

     public List<Dependent> createDependent(DependentInput input) {
          UUID userId = UUID.fromString(authService.getUserContext().getUserId());
          Optional<User> user = userRepository.findById(userId);
          List<Dependent> dependents = userMapper.toDependentList(input.getDependents());
          dependents.forEach(dependent -> {
               dependent.setDependentID(UUID.randomUUID());
               user.get().addDependent(dependent);
          });
          return dependentRespository.saveAll(dependents);
     }

     public List<Dependent> updtaDependents(DependentInput input) {
          UUID userId = UUID.fromString(authService.getUserContext().getUserId());
          List<Dependent> dependents = userMapper.toDependentList(input.getDependents());
          dependents.forEach(dependent -> dependent.setDependentID(userId));
          return dependentRespository.saveAll(dependents);
     }

}
