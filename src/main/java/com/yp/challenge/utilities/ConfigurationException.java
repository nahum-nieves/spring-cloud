package com.yp.challenge.utilities;

public class ConfigurationException extends RuntimeException{
    public ConfigurationException(String reason){
        super(reason);
    }
    public ConfigurationException(String reason, Exception exception){
        super(reason,exception);
    }
}
