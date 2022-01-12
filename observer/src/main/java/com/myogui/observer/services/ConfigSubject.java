package com.myogui.observer.services;

import com.myogui.observer.utils.ConfigObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class ConfigSubject {
    Logger logger = LogManager.getLogger(ConfigSubject.class);

    private Set<ConfigObserver> observers;

    public ConfigSubject() {
        observers = Collections.synchronizedSet(new HashSet<>());
    }

    public void addObserver(ConfigObserver observer) {
        logger.info("Se agrego un nuevo observer a la lista.");
        observers.add(observer);
    }

    public void removeObserver(ConfigObserver observer) {
        logger.info("Se remueve un observer de la lista.");
        observers.remove(observer);
    }

    public void notifyAllObservers(Object event) {
        logger.info("Se notifica a todos los observers de la lista.");
        synchronized (observers) {
            observers.forEach(obs -> obs.updateConfig(event));
        }
    }
}
