package am.threesmart.mappers;

import am.threesmart.models.dto.EmployeeRegisterRequestDetails;
import am.threesmart.models.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    EmployeeEntity registerRequestToEntity(EmployeeRegisterRequestDetails employeeRegisterRequestDetails);

}
