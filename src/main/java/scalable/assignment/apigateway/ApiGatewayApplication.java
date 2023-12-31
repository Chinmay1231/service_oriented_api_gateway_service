/*
 * ApiGatewayApplication.java -- Defines ApiGatewayApplication class 
 * This code is implemented as part of assignment given to group #3 for  
 * course Scalable Service of MTECH Program Software Engineering
 * Assignment Group #3
 * Student Name : Yogesh Kshatriya
 * Student Id   : 2022MT93005
 * Course       : Scalable Services
 * Program      : MTECH Software Engineering
 * Student Email: 2022MT93005@wilp.bits-pilani.ac.in
 */

package scalable.assignment.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * The <code>ApiGatewayApplication</code> launches ApiGateway 
 * It has main method which serves as entry point
 * @author Yogesh Kshatriya
 */
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
    
    /**
     * Configures instance of CustomRouteLocator. 
     * @param builder        - default route locator builder
     * @param repo           - database repository for route entity
     * @return               - instance of CustomRouteLocator
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, 
        RouteRepository repo) {
        return new CustomRouteLocator(builder, repo);
    }
    
    /**
     * Configure Api Gateway Security Filter
     * @return              - instance of ApiGatewaySecurityFilter
     */
    @Bean
    public GlobalFilter getApiGatewaySecurityFilter() {
        return new ApiGatewaySecurityFilter();
    }
    
    @Bean
    public JwtUtil getJwtUtil() {
        return new JwtUtil();
    }
}
