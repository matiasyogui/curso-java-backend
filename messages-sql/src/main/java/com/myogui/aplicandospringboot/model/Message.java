package com.myogui.aplicandospringboot.model;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class Message {
    @Id @GeneratedValue
    private Long id;
    private String description;

    public Message(){}
    public Message(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
