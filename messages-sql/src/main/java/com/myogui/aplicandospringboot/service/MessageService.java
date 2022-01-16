package com.myogui.aplicandospringboot.service;

import com.myogui.aplicandospringboot.handle.FirstApplicationException;
import com.myogui.aplicandospringboot.model.Message;
import com.myogui.aplicandospringboot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public Message createMessage(Message message) {
        return repository.save(message);
    }

    public List<Message> getAllMessages() {
        return (List<Message>) this.repository.findAll();
    }

    public Message getMessageById(Long id) throws FirstApplicationException {
        Optional<Message> m = repository.findById(id);

        if(m.isPresent()) {
            return m.get();
        }

        throw new FirstApplicationException("Mensaje no encontrado");
    }

    public Message updateMessage(Message message, Long id) throws FirstApplicationException {

        Optional<Message> m = repository.findById(id);

        if(m.isPresent()) {
            message.setId(id);
            return repository.save(message);
        }
        throw new FirstApplicationException("Mensaje no encontrado");
    }

    public Message deleteMessageById(Long id) throws FirstApplicationException {

        Optional<Message> m = repository.findById(id);

        if(m.isPresent()) {
            repository.deleteById(id);
            return m.get();
        }
        throw new FirstApplicationException("Mensaje no encontrado");
    }
}
