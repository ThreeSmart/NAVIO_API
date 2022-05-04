package am.threesmart.services;

import am.threesmart.mappers.MessagesMapper;
import am.threesmart.models.dto.Message;
import am.threesmart.models.entity.MessagesEntity;
import am.threesmart.repositories.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public MessagesService(final MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public Message pushMessage(final Message message) {
        final MessagesEntity messagesEntity = MessagesMapper.instance.dtoToEntity(message);
        final MessagesEntity saved = messagesRepository.save(messagesEntity);
        return MessagesMapper.instance.entityToDto(saved);
    }

    public List<Message> getAllMessages(final long fromUserId, final long toUserId) {
        final List<MessagesEntity> fromTo = messagesRepository.findAllByFromUserIdAndToUserId(fromUserId, toUserId);
        final List<MessagesEntity> toFrom = messagesRepository.findAllByFromUserIdAndToUserId(toUserId, fromUserId);

        final List<Message> result = new ArrayList<>();

        for (final MessagesEntity messagesEntity : fromTo) {
            result.add(MessagesMapper.instance.entityToDto(messagesEntity));
        }

        for (final MessagesEntity messagesEntity : toFrom) {
            result.add(MessagesMapper.instance.entityToDto(messagesEntity));
        }

        return result;
    }

}
