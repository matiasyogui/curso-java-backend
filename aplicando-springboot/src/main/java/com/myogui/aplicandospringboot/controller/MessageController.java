package com.myogui.aplicandospringboot.controller;

import com.myogui.aplicandospringboot.handle.FirstApplicationException;
import com.myogui.aplicandospringboot.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/messages")
public class MessageController {
    Logger logger = LogManager.getLogger(MessageController.class);

    @GetMapping("/")
    public List<Message> getMessages() throws FirstApplicationException {
        logger.info("GET Resquest all messages");

        if(listOfMessages().isEmpty()) {
            throw new FirstApplicationException("Message list is empty.");
        }

        return listOfMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) throws FirstApplicationException {
        logger.info("GET Request message by id " + id);
        if(id < 1 ||  id > 3){
            throw new FirstApplicationException("ID must be > 0");
        }

        var messageFiltered = listOfMessages().stream().filter(msg -> Objects.equals(msg.getId(), id));
        return messageFiltered.findFirst().orElse(new Message(0L, "Message not found."));
    }

    public List<Message> listOfMessages() {
        return List.of(new Message(1L, "First message"),
                       new Message(2L, "Second message"),
                       new Message(3L, "Third message"));
    }
}
