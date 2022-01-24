package com.myogui.nginx8082.controller;

import com.myogui.nginx8082.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class MessageController {

    List<Message> messages = List.of(
            new Message("Mensaje 8082 0"),
            new Message("mensaje 8082 1"),
            new Message("mensaje 8082 2")
    );

    @GetMapping("/")
    public List<Message> getMensajes(@RequestParam(required = false) String name) {
        return messages;
    }
}
