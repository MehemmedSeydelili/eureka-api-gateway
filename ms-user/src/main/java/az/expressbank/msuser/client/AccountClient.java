package az.expressbank.msuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account-service", url = "http://localhost:8020/MS-ACCOUNT/api/account")
public interface AccountClient {

    @GetMapping()
    String getAccount();

}
