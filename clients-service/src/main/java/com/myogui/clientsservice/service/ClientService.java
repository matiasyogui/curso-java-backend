package com.myogui.clientsservice.service;

import com.myogui.clientsservice.AnnotationForMethods;
import com.myogui.clientsservice.handle.ClientException;
import com.myogui.clientsservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    Logger logger = LogManager.getLogger(ClientService.class);


    public Client create(Client client) {
        logger.info("Se creo un cliente.");
        return client;
    }

    public void get() {
        logger.info("Se listaron todos los clientes.");
    }

    @AnnotationForMethods
    public Client update(Client client) throws ClientException {
        if(client.getNombre().isEmpty()) {
            throw new ClientException("El cliente no existe.");
        }

        logger.info("Se actualizo al cliente " + client.getId());
        return client;
    }

    @AnnotationForMethods
    public void delete(Long id) {
        logger.info("Se elimino al cliente " + id);
    }
}
