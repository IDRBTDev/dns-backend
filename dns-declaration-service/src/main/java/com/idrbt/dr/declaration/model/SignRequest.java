package com.idrbt.dr.declaration.model;

public class SignRequest {
    private String tokenPin;
    private String dataToSign;
    //private String configPath;

    // Getters and Setters
    public String getTokenPin() {
        return tokenPin;
    }

    public void setTokenPin(String tokenPin) {
        this.tokenPin = tokenPin;
    }

    public String getDataToSign() {
        return dataToSign;
    }

    public void setDataToSign(String dataToSign) {
        this.dataToSign = dataToSign;
    }

//    public String getConfigPath() {
//        return configPath;
//    }
//
//    public void setConfigPath(String configPath) {
//        this.configPath = configPath;
//    }
}
