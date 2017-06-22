package br.com.happhour;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import br.com.happhour.config.ApplicationProperties;
import br.com.happhour.config.DefaultProfileUtil;


@ComponentScan
@EnableAutoConfiguration()
@EnableConfigurationProperties({ LiquibaseProperties.class, ApplicationProperties.class })
public class HappyendApplication {

	private static final Logger log = LoggerFactory.getLogger(HappyendApplication.class);

	private final Environment env;

	public HappyendApplication(Environment env) {
        this.env = env;
    }

	/**
	 * Initializes happyhr.
	 * <p>
	 * Spring profiles can be configured with a program arguments
	 * --spring.profiles.active=your-active-profile
	 */
	@PostConstruct
	public void initApplication() {
		Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (activeProfiles.contains(DefaultProfileUtil.SPRING_PROFILE_DEVELOPMENT)
				&& activeProfiles.contains(DefaultProfileUtil.SPRING_PROFILE_PRODUCTION)) {
			log.error("You have misconfigured your application! It should not run "
					+ "with both the 'dev' and 'prod' profiles at the same time.");
		}
		if (activeProfiles.contains(DefaultProfileUtil.SPRING_PROFILE_DEVELOPMENT)
				&& activeProfiles.contains(DefaultProfileUtil.SPRING_PROFILE_CLOUD)) {
			log.error("You have misconfigured your application! It should not"
					+ "run with both the 'dev' and 'cloud' profiles at the same time.");
		}
	}

	public static void main(String[] args) throws UnknownHostException {

		SpringApplication app = new SpringApplication(HappyendApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";

		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t"
						+ "Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol,
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
	}
}
