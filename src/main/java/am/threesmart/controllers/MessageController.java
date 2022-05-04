package am.threesmart.controllers;

import am.threesmart.models.dto.SentMessage;
import am.threesmart.services.MessagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@PreAuthorize("permitAll()")
public class MessageController {

    private final MessagesService messagesService;

    public MessageController(final MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody SentMessage sentMessage) {
        final SentMessage sent = messagesService.pushMessage(sentMessage);
        return ResponseEntity.ok("sent message with id: " + sent.getId());
    }

}
