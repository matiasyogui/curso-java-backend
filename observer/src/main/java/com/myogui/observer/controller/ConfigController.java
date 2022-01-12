package com.myogui.observer.controller;

import com.myogui.observer.domain.UserConfig;
import com.myogui.observer.services.ConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    Logger logger = LogManager.getLogger(ConfigController.class);

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PutMapping("/users")
    public UserConfig updateUserConfig(@RequestBody UserConfig userConfig) {
        logger.info("PUT request.");
        configService.updateConfigUser(userConfig);
        return userConfig;
    }
}
