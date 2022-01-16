package com.myogui.aplicandospringboot.repository;

import com.myogui.aplicandospringboot.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
