package am.threesmart.mappers;

import am.threesmart.models.dto.UserRegisterRequestDetails;
import am.threesmart.models.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    UserEntity registerRequestToEntity(UserRegisterRequestDetails userRegisterRequestDetails);

}
