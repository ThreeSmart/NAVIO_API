package am.threesmart.mappers;

import am.threesmart.models.dto.Task;
import am.threesmart.models.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper instance = Mappers.getMapper(TaskMapper.class);

    TaskEntity dtoToEntity(Task task);

    Task entityToDto(TaskEntity entity);
}
