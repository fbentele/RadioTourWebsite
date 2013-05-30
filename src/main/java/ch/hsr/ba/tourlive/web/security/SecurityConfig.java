package ch.hsr.ba.tourlive.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:spring/security.xml" })
public class SecurityConfig {
	public SecurityConfig() {
		super();
	}
}
