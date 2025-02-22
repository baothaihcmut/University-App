package com.universityapp.modules.auth.interactors;

import com.universityapp.modules.auth.presenters.input.ConfirmSignUpInput;
import com.universityapp.modules.auth.presenters.input.LoginInput;
import com.universityapp.modules.auth.presenters.input.SignUpInput;
import com.universityapp.modules.auth.presenters.output.ConfirmSignUpOutput;
import com.universityapp.modules.auth.presenters.output.LoginOutput;

public interface AuthInteractor {
    LoginOutput logIn(LoginInput input) throws Exception;

    void signUp(SignUpInput input) throws Exception;


    ConfirmSignUpOutput confirmSignUp(ConfirmSignUpInput input) throws Exception;
}
