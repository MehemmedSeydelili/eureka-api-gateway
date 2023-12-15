package az.expressbank.apigateway.config;

import az.expressbank.apigateway.config.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service-route", r -> r.path("/MS-USER/v3/api-docs")
                        .uri("lb://MS-USER"))

                .route("user-service-route", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ms-user"))

                .route("account-service-route", r -> r.path("/api/account/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ACCOUNT-SERVICE"))

                .route("account-service-route", r -> r.path("/api/account/**", "/swagger-ui.html")
                        .uri("lb://ACCOUNT-SERVICE"))

                .route("security-service-route", r -> r.path("/auth/**", "/auth-service/v3/api-docs")
                        .uri("lb://SECURITY-SERVICE"))
                .build();
    }

}
