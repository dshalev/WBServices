package com.wisebreeze.services.scheduling.service.Resources;

import com.codahale.metrics.annotation.Timed;
import com.wisebreeze.services.scheduling.service.api.Saying;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/scheduling-service")
@Produces(MediaType.APPLICATION_JSON)
public class SchedulingServiceResource {
    private final String template;
    private final AtomicLong counter;


    public SchedulingServiceResource(String template) {
        this.template = template;
        this.counter = new AtomicLong();
    }


    @GET
    @Timed
    public Saying sayHello(@DefaultValue("STRANGER") @QueryParam("name") String name) {
        final String value = String.format(template, name);
        return new Saying(counter.incrementAndGet(), value);
    }
}
