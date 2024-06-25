package nl.wc.umpire_quiz.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.util.EnvironmentUtil.getFrontendUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CorsFilterTest {
    private final CorsFilter sut = new CorsFilter();

    @Test
    void testCorsFilter() {
        ContainerRequestContext requestContextMock = mock(ContainerRequestContext.class);
        ContainerResponseContext responseContextMock = mock(ContainerResponseContext.class);
        MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
        when(responseContextMock.getHeaders()).thenReturn(headers);

        sut.filter(requestContextMock, responseContextMock);

        assertThat(headers).containsEntry("Access-Control-Allow-Origin", List.of(getFrontendUrl()))
                           .containsEntry("Access-Control-Allow-Credentials", List.of("true"))
                           .containsEntry("Access-Control-Allow-Headers", List.of("origin, content-type, accept, authorization"))
                           .containsEntry("Access-Control-Expose-Headers", List.of("authorization"))
                           .containsEntry("Access-Control-Allow-Methods", List.of("GET, POST, PUT, DELETE, OPTIONS, HEAD"));

        verify(responseContextMock, times(1)).getHeaders();
    }

}