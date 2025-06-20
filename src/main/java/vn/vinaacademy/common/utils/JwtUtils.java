package vn.vinaacademy.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.server.reactive.ServerHttpRequest;

@UtilityClass
public class JwtUtils {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        return getJwtToken(authHeader);
    }

    public static String getJwtToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(AUTHORIZATION_HEADER);
        return getJwtToken(authHeader);
    }

    public static String getJwtToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.replace(BEARER_PREFIX, "");
        }
        return null;
    }
}
