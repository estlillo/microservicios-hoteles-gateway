package cl.barbatos.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class GatewayConfig {

    @Bean("routesConfig")
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/hotels/**")
                        .filters(f -> f.rewritePath("/api/hotels/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://HOTELS")).
                route(p -> p
                        .path("/api/rooms/**")
                        .filters(f -> f.rewritePath("/api/rooms/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://ROOMS")).
                route(p -> p
                        .path("/api/reservations/**")
                        .filters(f -> f.rewritePath("/api/reservations/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://RESERVATIONS")).build();
    }

}
