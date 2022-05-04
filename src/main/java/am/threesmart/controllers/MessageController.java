package am.threesmart.controllers;

import am.threesmart.models.dto.SentMessage;
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

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody SentMessage sentMessage) {

        return ResponseEntity.ok("stacel em @ngers namakd");
    }

}
