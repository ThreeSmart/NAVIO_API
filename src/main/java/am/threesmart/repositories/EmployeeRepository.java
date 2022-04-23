package am.threesmart.repositories;

import am.threesmart.models.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByUsername(String username);

}
