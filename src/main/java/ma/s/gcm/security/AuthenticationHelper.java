package ma.s.gcm.security;

import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.BureauEtudeException;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthenticationHelper {

    private static KeycloakAuthenticationToken getAuthentication() throws BureauEtudeException {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(AuthenticationHelper::isInstanceOfKeycloakAuthenticationToken)
                .orElseThrow(() -> portalException("Authentication context is null"));
    }

    private static KeycloakAuthenticationToken isInstanceOfKeycloakAuthenticationToken(Authentication auth) {
        if (!(auth instanceof KeycloakAuthenticationToken)) {
            throw portalException("Authentication  is an instance of KeycloakAuthenticationToken");
        }
        return (KeycloakAuthenticationToken) auth;
    }

    private static AccessToken getCurrentAccessToken() throws BureauEtudeException {
        return Optional.ofNullable(getAuthentication().getCredentials())
                .map(AuthenticationHelper::isInstanceOfKeycloakSecurityContext)
                .orElseThrow(() -> portalException("Retrieved access token from credential is null"));
    }

    private static AccessToken isInstanceOfKeycloakSecurityContext(Object credentials) {
        if (!(credentials instanceof KeycloakSecurityContext)) {
            throw portalException("keycloakAuthenticationToken's credentials are not instance of KeycloakSecurityContext");
        }
        return ((KeycloakSecurityContext) credentials).getToken();
    }

    public static String getCurrentUsername() throws BureauEtudeException {
        return "rabii";
//        return Optional.ofNullable(getCurrentAccessToken().getPreferredUsername())
//                .orElseThrow(() -> portalException("Retrieved username from access token is null"));
    }

    private static BureauEtudeException portalException(String message) {
        return new BureauEtudeException(ExceptionCode.API_ERROR_INTERNAL, "Authentication error", message);
    }

}
