package org.acme.eval.resource;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greet")
public class GreetingResource {

    private final Counter apiCounter;

    @Inject
    public GreetingResource(MeterRegistry meterRegistry) {
        apiCounter = meterRegistry.counter("greeting.api.calls");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        apiCounter.increment();
        return "Hello RESTEasy";
    }
}