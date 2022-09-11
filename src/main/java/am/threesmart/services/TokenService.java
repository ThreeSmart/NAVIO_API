package am.threesmart.services;

import am.threesmart.models.entity.TokenEntity;
import am.threesmart.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(final TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String generateOneTimeToken(final Long userId) {
        final TokenEntity saved = tokenRepository.save(new TokenEntity(UUID.randomUUID().toString(), userId));
        return saved.getValue();
    }

    public TokenEntity getToken(final String token) {
        final Optional<TokenEntity> byValue = tokenRepository.findByValue(token);
        return byValue.orElse(null);
    }

    public void expireToken(final TokenEntity token) {
        token.setExpired(true);
        tokenRepository.save(token);
    }
}
