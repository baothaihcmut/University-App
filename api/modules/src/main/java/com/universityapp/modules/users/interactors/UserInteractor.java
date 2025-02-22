package com.universityapp.modules.users.interactors;

import java.util.List;
import java.util.UUID;

import com.universityapp.modules.users.entities.Dependent;
import com.universityapp.modules.users.presenters.input.DependentInput;

public interface UserInteractor {
     List<Dependent> createDependent(DependentInput input);

     List<Dependent> getDependentsByUserId();

     void deleteDependent(UUID dependent_id);
}
