package am.threesmart.repositories;

import am.threesmart.models.entity.TokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, Long> {

    Optional<TokenEntity> findByValue(String value);

}
