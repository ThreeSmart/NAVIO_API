package am.threesmart.services;

import am.threesmart.mappers.MessagesMapper;
import am.threesmart.models.dto.SentMessage;
import am.threesmart.models.entity.MessagesEntity;
import am.threesmart.repositories.MessagesRepository;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public MessagesService(final MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public SentMessage pushMessage(final SentMessage sentMessage) {
        final MessagesEntity messagesEntity = MessagesMapper.instance.dtoToEntity(sentMessage);
        final MessagesEntity saved = messagesRepository.save(messagesEntity);
        return MessagesMapper.instance.entityToDto(saved);
    }

}
