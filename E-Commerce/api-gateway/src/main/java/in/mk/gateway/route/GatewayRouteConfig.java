package in.mk.gateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()

                // AUTH SERVICE (PUBLIC)
                .route("auth-service", r -> r
                        .path("/auth/**")
                        .uri("lb://auth-service")
                )

                // USER + ADMIN SERVICE
                .route("user-service", r -> r
                        .path("/user/**", "/admin/**")
                        .filters(f -> f
                                .circuitBreaker(c -> c
                                        .setName("defaultCircuitBreaker")
                                        .setFallbackUri("forward:/fallback"))
                                .retry(3)
                        )
                        .uri("lb://user-service")
                )

                // ORDER SERVICE
                .route("order-service", r -> r
                        .path("/orders/**")
                        .filters(f -> f
                                .retry(3)
                                .circuitBreaker(c -> c
                                        .setName("defaultCircuitBreaker")
                                        .setFallbackUri("forward:/fallback"))
                        )
                        .uri("lb://order-service")
                )
                
                //PRODUCT-SERVICE
                .route("product-service", r -> r
                	    .path("/product/**", "/category/**")
                	    .filters(f -> f
                	        .circuitBreaker(c -> c
                	            .setName("defaultCircuitBreaker")
                	            .setFallbackUri("forward:/fallback")
                	        )
                	    )
                	    .uri("lb://product-service")
                	)

                //INVENTORY SERVICE
                .route("inventory-service", r -> r
                	    .path("/inventory/**")
                	    .filters(f -> f
                	        .circuitBreaker(c -> c
                	            .setName("defaultCircuitBreaker")
                	            .setFallbackUri("forward:/fallback")
                	        )
                	    )
                	    .uri("lb://inventory-service")
                	)
                
                //CART SERVICE
                .route("cart-service", r -> r
                	    .path("/cart/**")
                	    .filters(f -> f
                	        .circuitBreaker(c -> c
                	            .setName("defaultCircuitBreaker")
                	            .setFallbackUri("forward:/fallback")
                	        )
                	    )
                	    .uri("lb://cart-service")
                	)
                
                //ORDER SERVICE
                .route("order-service", r -> r
                	    .path("/order/**")
                	    .filters(f -> f
                	        .circuitBreaker(c -> c
                	            .setName("defaultCircuitBreaker")
                	            .setFallbackUri("forward:/fallback")
                	        )
                	    )
                	    .uri("lb://order-service")
                	)
                
                //SWAGGER-CONFIGURATION
                
                // AUTH SERVICE SWAGGER
                .route("auth-service-swagger", r -> r
                        .path("/auth-service/swagger-ui/**", "/auth-service/v3/api-docs/**")
                        .filters(f -> f
                                .rewritePath("/auth-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://auth-service")
                )

              
                // USER SERVICE SWAGGER
                .route("user-service-swagger", r -> r
                        .path("/user-service/swagger-ui/**",
                        	"/user-service/v3/api-docs/**")
                        .filters(f -> f
                                .rewritePath("/user-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://user-service")
                )

                // ORDER SERVICE SWAGGER
                .route("order-service-swagger", r -> r
                        .path("/order-service/swagger-ui/**", "/order-service/v3/api-docs/**")
                        .filters(f -> f
                                .rewritePath("/order-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://order-service")
                )


                // PRODUCT SERVICE SWAGGER
                .route("product-service-swagger", r -> r
                        .path(
                                "/product-service/swagger-ui/**",
                                "/product-service/v3/api-docs/**"
                        )
                        .filters(f -> f
                                .rewritePath("/product-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://product-service")
                )

                // INVENTORY SERVICE SWAGGER
                .route("inventory-service-swagger", r -> r
                        .path(
                                "/inventory-service/swagger-ui/**",
                                "/inventory-service/v3/api-docs/**"
                        )
                        .filters(f -> f
                                .rewritePath("/inventory-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://inventory-service")
                )

                // CART SERVICE SWAGGER
                .route("cart-service-swagger", r -> r
                        .path(
                                "/cart-service/swagger-ui/**",
                                "/cart-service/v3/api-docs/**"
                        )
                        .filters(f -> f
                                .rewritePath("/cart-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://cart-service")
                )

                .build();
    }
}
