package com.sniperfuchs.servicebroker.config;

import com.sniperfuchs.servicebroker.repository.ServiceOfferingRepository;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@EnableMongoRepositories(basePackageClasses = ServiceOfferingRepository.class)
@Configuration
@EnableWebMvc
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer { //TODO: Split into multiple configurations cause this is getting messy

    // Header interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/v2/**").excludePathPatterns("/v2/api-docs");
    }

    // Basic authentification

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/v2/catalog", "/v2/service_instances/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Swagger configuration

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sniperfuchs.servicebroker.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(operationParameters());
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title("API Doc for implementation of Open Service Broker")
                .description("Personal project trying to create a fully functional service broker.")
                .version("1.0.0")
                .build();
    }

    private List<Parameter> operationParameters() {
        List<Parameter> headers = new ArrayList<>();

        headers.add(new ParameterBuilder().name("X-Broker-API-Version")
                .description("API version that should be used")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).defaultValue("2.16").build());

        headers.add(new ParameterBuilder().name("Authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).defaultValue("Basic " + Base64.getEncoder().encodeToString("user:password".getBytes())).build());
        return headers;
    }
}
