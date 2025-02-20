package com.universityapp.modules.auth.interactors;

import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.output.LoginOutput;

public interface AuthInteractor {
    LoginOutput logIn(LoginInput input)  throws Exception;
}
