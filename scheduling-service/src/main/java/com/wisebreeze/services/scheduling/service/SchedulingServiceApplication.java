package com.wisebreeze.services.scheduling.service;

import com.wisebreeze.services.scheduling.service.Resources.SchedulingServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dshalev on 26/10/2016.
 */
public class SchedulingServiceApplication extends Application<SchedulingServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new SchedulingServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<SchedulingServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(SchedulingServiceConfiguration schedulingServiceConfiguration, Environment environment) throws Exception {
        final SchedulingServiceResource resource = new SchedulingServiceResource(
                schedulingServiceConfiguration.getTemplate()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(schedulingServiceConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
