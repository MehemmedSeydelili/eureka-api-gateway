package az.expressbank.msuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = ("ms-account"))
@Component
public interface AccountFeignClient {

    @GetMapping("/api/accounts")
    String getAccount();

}
