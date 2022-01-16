package com.myogui.aplicandospringboot.controller;

import com.myogui.aplicandospringboot.handle.FirstApplicationException;
import com.myogui.aplicandospringboot.model.Message;
import com.myogui.aplicandospringboot.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/messages")
public class MessageController {
    Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @GetMapping("/")
    public ResponseEntity<List<Message>> getMessages() throws FirstApplicationException {
        logger.info("GET Resquest all messages");

        return new ResponseEntity<>(this.messageService.getAllMessages(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        logger.info("GET Request message by id " + id);
        try{
            return new ResponseEntity<>(this.messageService.getMessageById(id), HttpStatus.OK);
        } catch (FirstApplicationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Message> createMessage(@RequestBody @Validated Message message) {
        logger.info("POST request message.");

        return new ResponseEntity<>(this.messageService.createMessage(message), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> putMessage(@RequestBody Message message, @PathVariable Long id) {
        logger.info("PUT request message");

        try{
            return new ResponseEntity<>(this.messageService.updateMessage(message, id), HttpStatus.OK);
        } catch (FirstApplicationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id) {
        logger.info("DELETE request message");

        try{
            return new ResponseEntity<>(this.messageService.deleteMessageById(id), HttpStatus.OK);
        } catch (FirstApplicationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    public List<Message> listOfMessages() {
        return List.of(new Message(1L, "First message"),
                       new Message(2L, "Second message"),
                       new Message(3L, "Third message"));
    }
}
