package az.expressbank.msuser.service;

import az.expressbank.msuser.client.AccountClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountClient accountClient;

    public String getUserAccount(){
        return accountClient.getAccount();
    }
}
