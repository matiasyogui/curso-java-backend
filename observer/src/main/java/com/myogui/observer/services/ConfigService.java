package com.myogui.observer.services;

import com.myogui.observer.domain.UserConfig;
import com.myogui.observer.utils.ConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    private final ConfigSubject configSubject;

    @Value(value = "${user.test.rol}")
    private String userRol;

    @Value(value = "${user.test.email}")
    private String userEmail;

    @Value(value = "${user.test.phone}")
    private String userPhone;

    @Autowired
    public ConfigService(ConfigSubject configSubject) {
        this.configSubject = configSubject;
    }

    public void updateConfigUser(UserConfig userConfig) {
        userRol = userConfig.getRol();
        userEmail = userConfig.getEmail();
        userPhone = userConfig.getPhone();
        configSubject.notifyAllObservers(ConfigType.USER);
    }

    public String getUserRol() {
        return userRol;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
