package am.threesmart.config;

import am.threesmart.jwt.JWTService;
import am.threesmart.models.dto.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JWTFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {
        final JWTService jwtService = new JWTService();

        final String jwtToken = ((HttpServletRequest) request).getHeader("Authorization");

        // <logs>
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.printf("Request came with JWT '%s', %s\n", jwtToken, formatter.format(LocalDateTime.now()));
        // </logs>

        final String token = jwtService.validateToken(jwtToken);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final Jws<Claims> tokenClaims = jwtService.parse(token);
        final Claims body = tokenClaims.getBody();

        final String id = body.get("id").toString();
        final String username = body.get("username").toString();
        final String name = body.get("name").toString();
        final String surname = body.get("surname").toString();
        final String email = body.get("email").toString();
        final Long departmentId = Long.valueOf(body.get("department_id").toString());

        final AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(id);
        authenticatedUser.setUsername(username);
        authenticatedUser.setName(name);
        authenticatedUser.setSurname(surname);
        authenticatedUser.setEmail(email);
        authenticatedUser.setDepartmentId(departmentId);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        id, authenticatedUser
                )
        );

        filterChain.doFilter(request, response);
    }

}
