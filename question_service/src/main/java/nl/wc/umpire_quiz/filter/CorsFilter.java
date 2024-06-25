package nl.wc.umpire_quiz.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

import static nl.wc.umpire_quiz.util.EnvironmentUtil.getFrontendUrl;

@Provider
@Priority(1)
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", getFrontendUrl());
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.add("Access-Control-Expose-Headers", "authorization"); // so scripts are allowed to read this header(s)
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
