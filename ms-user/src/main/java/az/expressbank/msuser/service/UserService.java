package az.expressbank.msuser.service;

import az.expressbank.msuser.client.AccountFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountFeignClient accountFeignClient;

    public String getUserAccount(){
        return accountFeignClient.getAccount();
    }
}
