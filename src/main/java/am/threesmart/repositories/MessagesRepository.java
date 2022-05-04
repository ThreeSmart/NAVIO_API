package am.threesmart.repositories;

import am.threesmart.models.entity.MessagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends CrudRepository<MessagesEntity, Long> {
}
