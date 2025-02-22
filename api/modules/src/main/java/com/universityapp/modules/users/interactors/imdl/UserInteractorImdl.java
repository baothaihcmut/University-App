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
import com.universityapp.modules.users.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInteractorImdl implements UserInteractor {
     private final AuthService authService;
     private final DependentRespository dependentRespository;
     private final UserRepository userRepository;
     private final UserMapper userMapper;

     public List<Dependent> getDependents() {
          return dependentRespository.findAll();
     }

     public List<Dependent> createDependent(DependentInput input) {
          UUID userId = UUID.fromString(authService.getUserContext().getUserId());
          User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

          List<Dependent> dependents = userMapper.toDependentList(input.getDependents());
          dependents.forEach(dependent -> {
               if (dependent.getDependentId() == null) {
                    dependent.setDependentId(UUID.randomUUID());
               }
               dependent.setUser(user);
          });

          return dependentRespository.saveAllAndFlush(dependents); // saveAll() hợp lệ hơn
     }

     public List<Dependent> updtaDependents(DependentInput input) {
          UUID userId = UUID.fromString(authService.getUserContext().getUserId());
          List<Dependent> dependents = userMapper.toDependentList(input.getDependents());
          dependents.forEach(dependent -> dependent.setDependentId(userId));
          return dependentRespository.saveAll(dependents);
     }

     public List<Dependent> getDependentsByUserId() {
          UUID user_id = UUID.fromString(authService.getUserContext().getUserId());
          List<Dependent> dependents = dependentRespository.findByUserId(user_id);
          if (dependents.size() == 0) {
               return null;
          } else {
               return dependents;
          }
     }

     public void deleteDependent(UUID dependent_id) {
          UUID user_id = UUID.fromString(authService.getUserContext().getUserId());
          Optional<Dependent> dependent = dependentRespository.findByUserIdAndDependentId(user_id, dependent_id);
          if (dependent.isPresent()) {
               dependentRespository.delete(dependent.get());
          } else {
               throw new RuntimeException("Dependent not found Or User No permission");
          }
     }

}
