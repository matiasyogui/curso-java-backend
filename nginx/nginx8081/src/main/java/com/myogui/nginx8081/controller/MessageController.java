package com.myogui.nginx8081.controller;

import com.myogui.nginx8081.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
public class MessageController {

    List<Message> messages = List.of(
            new Message("Mensaje 8081 0"),
            new Message("mensaje 8081 1"),
            new Message("mensaje 8081 2")
    );

    @GetMapping("/")
    public List<Message> getMensajes(@RequestParam(required = false) String name) {
        return messages;
    }
}

/*

        ##
        # Basic Settings
        ##

        sendfile on;
        tcp_nopush on;
        tcp_nodelay on;
        keepalive_timeout 65;
        types_hash_max_size 2048;
        # server_tokens off;

        # server_names_hash_bucket_size 64;
        # server_name_in_redirect off;


 */
