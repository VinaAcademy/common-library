package vn.vinaacademy.common.security.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import vn.vinaacademy.common.exception.UnauthorizedException;
import vn.vinaacademy.common.security.SecurityContextHelper;
import vn.vinaacademy.common.security.annotation.HasAnyRole;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationAspect {

    private final SecurityContextHelper securityContextHelper;

    @Before("@annotation(hasAnyRole)")
    public void checkMethodAuthorization(JoinPoint joinPoint, HasAnyRole hasAnyRole) {
        checkRoleAuthorization(hasAnyRole.value(), joinPoint);
    }

    @Before("@within(hasAnyRole) && !@annotation(vn.vinaacademy.common.security.annotation.HasAnyRole)")
    public void checkClassAuthorization(JoinPoint joinPoint, HasAnyRole hasAnyRole) {
        checkRoleAuthorization(hasAnyRole.value(), joinPoint);
    }

    private void checkRoleAuthorization(String[] requiredRoles, JoinPoint joinPoint) {
        try {
            if (!securityContextHelper.hasAnyRole(requiredRoles)) {
                String userRoles = String.join(", ", securityContextHelper.getCurrentUserRoles());
                String requiredRolesStr = String.join(", ", requiredRoles);

                log.warn("Access denied for user {} with roles [{}]. Required roles: [{}]. Method: {}",
                        securityContextHelper.getCurrentUserEmail(),
                        userRoles,
                        requiredRolesStr,
                        joinPoint.getSignature().toShortString());

                throw new UnauthorizedException("Access denied. Required roles: " + requiredRolesStr);
            }

            log.debug("Authorization successful for user {} accessing {}",
                    securityContextHelper.getCurrentUserEmail(),
                    joinPoint.getSignature().toShortString());

        } catch (SecurityException e) {
            log.error("Security context error: {}", e.getMessage());
            throw new UnauthorizedException("Authentication required");
        }
    }
}