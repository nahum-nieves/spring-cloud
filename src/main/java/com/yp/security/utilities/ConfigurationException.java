package com.yp.security.utilities;

public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String reason) {
        super(reason);
    }

    public ConfigurationException(String reason, Exception exception) {
        super(reason, exception);
    }
}
