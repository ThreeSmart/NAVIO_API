package am.threesmart.controllers;

import am.threesmart.models.dto.Message;
import am.threesmart.services.MessagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@PreAuthorize("permitAll()")
public class MessageController {

    private final MessagesService messagesService;

    public MessageController(final MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody Message message) {
        final Message sent = messagesService.pushMessage(message);
        return ResponseEntity.ok("sent message with id: " + sent.getId());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam String fromUserId, @RequestParam String toUserId) {
        final List<Message> messages = messagesService.getAllMessages(Long.parseLong(fromUserId), Long.parseLong(toUserId));
        return ResponseEntity.ok(messages);
    }

}
