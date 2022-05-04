package am.threesmart.repositories;

import am.threesmart.models.entity.CoordinatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinatesRepository extends CrudRepository<CoordinatesEntity, Long> {

    List<CoordinatesEntity> findAllByUserId(Long userId);

}
