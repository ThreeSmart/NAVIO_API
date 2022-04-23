package am.threesmart.jwt;

import am.threesmart.models.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Objects;

@Component
public class JWTService {

    private static final Key KEY = Keys.hmacShaKeyFor("akjsydfgn238i76t4549eu2w98ydewf896dfygcdsbaciu2!".getBytes());

    public String generate(final UserEntity user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("name", user.getName())
                .claim("surname", user.getSurname())
                .claim("email", user.getEmail())
                .claim("department_id", user.getDepartment_id())
                .signWith(KEY).compact();
    }

    public Jws<Claims> parse(final String token) {
        final JwtParser build = Jwts.parserBuilder().setSigningKey(KEY).build();
        return build.parseClaimsJws(token);
    }

    public String validateToken(final String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith("Bearer ") || Objects.equals("null", jwtToken)) {
            return null;
        }
        final String substring = jwtToken.substring(7);
        return Objects.equals(substring, "null") ? null : substring;
    }

}
