package com.universityapp.modules.users.presenters.input;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependentInput {

     private List<DependentDTO> dependents;
}
