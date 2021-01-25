package com.example.amravatiexpress.ModelClasses;

public class ServicesModal {
    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public ServicesModal() {
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public ServicesModal(String accessToken, String serviceName) {
        AccessToken = accessToken;
        ServiceName = serviceName;
    }

    private String AccessToken,ServiceName;

    }

