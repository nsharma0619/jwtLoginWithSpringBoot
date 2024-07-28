package com.nsharma.springSecurityDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserDetailsService userDetailsService){
		return args -> {
			UserDetails john = User.builder()
					.username("john")
					.password(passwordEncoder.encode("password"))
					.roles("USER").build();
			UserDetails admin = User.builder()
					.username("admin")
					.password(passwordEncoder.encode("password"))
					.roles("ADMIN").build();
			JdbcUserDetailsManager jdbcUserDetailsManager = (JdbcUserDetailsManager) userDetailsService;
			jdbcUserDetailsManager.createUser(john);
			jdbcUserDetailsManager.createUser(admin);
		};
	}

}
