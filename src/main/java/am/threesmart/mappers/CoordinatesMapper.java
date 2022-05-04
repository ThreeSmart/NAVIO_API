package am.threesmart.mappers;

import am.threesmart.models.dto.Coordinates;
import am.threesmart.models.entity.CoordinatesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper instance = Mappers.getMapper(CoordinatesMapper.class);

    CoordinatesEntity dtoToEntity(Coordinates message);

    Coordinates entityToDto(CoordinatesEntity message);

}
