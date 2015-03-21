package com.toaster;

import com.toaster.health.SourceCodeHealthCheck;
import com.toaster.resources.ToasterResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ToasterApplication extends Application<ToasterConfiguration> {

	public static void main(String[] args) throws Exception {
		new ToasterApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<ToasterConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(ToasterConfiguration configuration, Environment environment) {
		final ToasterResource resource = new ToasterResource(
				configuration.getTemplate(),
				configuration.getDefaultName()
		);

		final SourceCodeHealthCheck healthCheck =
				new SourceCodeHealthCheck(configuration.getTemplate());

		environment.healthChecks().register("sourcecode", healthCheck);
		environment.jersey().register(resource);
	}

}
