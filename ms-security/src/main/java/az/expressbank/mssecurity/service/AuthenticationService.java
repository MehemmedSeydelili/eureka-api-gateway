package az.expressbank.mssecurity.service;

import az.expressbank.mssecurity.data.dto.SignUpRequest;
import az.expressbank.mssecurity.data.entity.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);

}
