package com.universityapp.modules.users.interactors;

import java.util.List;

import com.universityapp.modules.users.entities.Dependent;
import com.universityapp.modules.users.presenters.input.DependentInput;

public interface UserInteractor {
     List<Dependent> createDependent(DependentInput input);
}
