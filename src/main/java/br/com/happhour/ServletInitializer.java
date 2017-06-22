package br.com.happhour;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import br.com.happhour.config.DefaultProfileUtil;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		/**
		 * set a default to use when no profile is configured.
		 */
		DefaultProfileUtil.addDefaultProfile(application.application());
		return application.sources(HappyendApplication.class);
	}

}
