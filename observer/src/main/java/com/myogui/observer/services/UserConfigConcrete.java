package com.myogui.observer.services;

import com.myogui.observer.utils.ConfigObserver;
import com.myogui.observer.utils.ConfigType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConfigConcrete implements InitializingBean, ConfigObserver {
    Logger logger = LogManager.getLogger(UserConfigConcrete.class);

    private final ConfigSubject configSubject;
    private final ConfigService configService;

    private Integer id;
    private String name;
    private String rol;
    private String email;
    private String phone;

    @Autowired
    public UserConfigConcrete(ConfigSubject configSubject, ConfigService configService) {
        this.configSubject = configSubject;
        this.configService = configService;
    }

    @Override
    public void updateConfig(Object event) {
        if(event.equals(ConfigType.USER)) {
            this.rol = configService.getUserRol();
            logger.info("Rol nuevo: " + this.rol);

            this.email = configService.getUserEmail();
            logger.info("Email nuevo: " + this.email);

            this.phone = configService.getUserPhone();
            logger.info("Telefono nuevo: " + this.phone);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        configSubject.addObserver(this);
    }
}
