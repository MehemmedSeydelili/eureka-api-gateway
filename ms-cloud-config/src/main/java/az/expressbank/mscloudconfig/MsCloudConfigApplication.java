package az.expressbank.mscloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCloudConfigApplication.class, args);
    }

}