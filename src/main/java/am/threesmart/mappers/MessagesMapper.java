package am.threesmart.mappers;

import am.threesmart.models.dto.Message;
import am.threesmart.models.entity.MessagesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessagesMapper {

    MessagesMapper instance = Mappers.getMapper(MessagesMapper.class);

    MessagesEntity dtoToEntity(Message message);

    Message entityToDto(MessagesEntity message);

}
