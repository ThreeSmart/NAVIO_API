package am.threesmart.repositories;

import am.threesmart.models.entity.MessagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<MessagesEntity, Long> {

    List<MessagesEntity> findAllByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}
