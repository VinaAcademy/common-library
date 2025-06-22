package vn.vinaacademy.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.vinaacademy.common.constant.AuthConstants;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;

@Component
@Slf4j
public class SecurityContextHelper {
    
    public static final String USER_ID_HEADER = "X-User-Id";
    public static final String USER_EMAIL_HEADER = "X-User-Email";
    public static final String USER_ROLES_HEADER = "X-User-Roles";
    
    public String getCurrentUserId() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            throw new SecurityException("No request context available");
        }
        
        String userId = request.getHeader(USER_ID_HEADER);
        if (userId == null) {
            throw new SecurityException("User ID not found in request headers");
        }
        return userId;
    }
    
    public UUID getCurrentUserIdAsUUID() {
        return UUID.fromString(getCurrentUserId());
    }
    
    public String getCurrentUserEmail() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            throw new SecurityException("No request context available");
        }
        
        String email = request.getHeader(USER_EMAIL_HEADER);
        if (email == null) {
            throw new SecurityException("User email not found in request headers");
        }
        return email;
    }
    
    public String[] getCurrentUserRoles() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            throw new SecurityException("No request context available");
        }
        
        String rolesHeader = request.getHeader(USER_ROLES_HEADER);
        if (rolesHeader == null || rolesHeader.trim().isEmpty()) {
            return new String[0];
        }
        
        return rolesHeader.split(",");
    }
    
    public boolean hasRole(String role) {
        String[] userRoles = getCurrentUserRoles();
        return Arrays.asList(userRoles).contains(role);
    }
    
    public boolean hasAnyRole(String... roles) {
        String[] userRoles = getCurrentUserRoles();
        return Arrays.stream(roles)
                .anyMatch(role -> Arrays.asList(userRoles).contains(role));
    }
    
    public boolean isAdmin() {
        return hasRole(AuthConstants.ADMIN_ROLE);
    }
    
    public boolean isStudent() {
        return hasRole(AuthConstants.STUDENT_ROLE);
    }
    
    public boolean isInstructor() {
        return hasRole(AuthConstants.INSTRUCTOR_ROLE);
    }
    
    public boolean isStaff() {
        return hasRole(AuthConstants.STAFF_ROLE);
    }
    
    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs != null ? attrs.getRequest() : null;
    }
}