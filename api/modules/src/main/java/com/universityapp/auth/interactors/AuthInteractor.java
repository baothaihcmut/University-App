package com.universityapp.auth.interactors;

import com.universityapp.auth.presenters.input.LoginInput;
import com.universityapp.auth.presenters.output.LoginOutput;

public interface AuthInteractor {
    LoginOutput logIn(LoginInput input)  throws Exception;
}
