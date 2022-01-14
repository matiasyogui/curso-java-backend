package com.myogui.clientsservice.controller;

import com.myogui.clientsservice.handle.ClientException;
import com.myogui.clientsservice.model.Client;
import com.myogui.clientsservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/")
    public Client createClient(@RequestBody Client client) {
        return this.clientService.create(client);
    }

    @GetMapping("/")
    public void getClients() {
        this.clientService.get();
    }

    @PutMapping("/{id}")
    public Client updateClient(@RequestBody Client client) throws ClientException {
        return this.clientService.update(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        this.clientService.delete(id);
    }
}
