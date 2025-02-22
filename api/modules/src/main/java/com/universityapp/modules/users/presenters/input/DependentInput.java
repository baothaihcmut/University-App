package com.universityapp.modules.users.presenters.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DependentInput {
     List<DependentDTO> dependents;
}
