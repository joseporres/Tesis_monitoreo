package de.fstab.legacyjavaapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.common.TextFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces; // Import for the @Produces annotation
import javax.ws.rs.core.MediaType; // Import for MediaType
import java.io.StringWriter;


import java.util.concurrent.TimeUnit;

import java.io.Writer;
import java.io.IOException; // Import for IOException



@Component
@Path("")
public class RestEndpoint {
    private final Counter promRequestsTotal = Counter.build()
            .name("total_request")
            .help("Total number of requests.")
            .register();

    private final Logger logger = LoggerFactory.getLogger(RestEndpoint.class);

    @GET
    @Path("/hello/{user}")
    public String message(@PathParam("user") String user) {
        long start = System.nanoTime();
        promRequestsTotal.inc();
        try {
            return "Hello, " + user + "!";
        } finally {
            log("/hello/" + user, start);
        }
    }

    @GET
    @Path("/throw")
    public Response throwException() {
        long start = System.nanoTime();
        try {
            throw new RuntimeException("Something unexpected happened.");
        } catch(Exception e) {
            log("/throw", start, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/sleep/{milliseconds}")
    public String message(@PathParam("milliseconds") int milliseconds) throws InterruptedException {
        long start = System.nanoTime();
        try {
            Thread.sleep(milliseconds);
            return "Woke up after " + milliseconds + "ms.";
        } finally {
            log("/sleep/" + milliseconds, start);
        }
    }

    private void log(String path, long startTimestampNanos) {
        log(path, startTimestampNanos, null);
    }

    private void log(String path, long startTimestampNanos, Throwable t) {
        double durationSeconds = (System.nanoTime()-startTimestampNanos)/(double) TimeUnit.SECONDS.toNanos(1);
        String message = String.format("GET %s - duration %.9fs", path, durationSeconds);
        if (t != null) {
            logger.error(message + ": " + t.getMessage(), t);
        } else {
            logger.info(message);
        }
    }
}
