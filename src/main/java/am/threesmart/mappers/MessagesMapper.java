package am.threesmart.mappers;

import am.threesmart.models.dto.SentMessage;
import am.threesmart.models.entity.MessagesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessagesMapper {

    MessagesMapper instance = Mappers.getMapper(MessagesMapper.class);

    MessagesEntity dtoToEntity(SentMessage message);

    SentMessage entityToDto(MessagesEntity message);

}
