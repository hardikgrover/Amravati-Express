package com.example.amravatiexpress.ModelClasses;

public class ShopDetailsModal {
    public Long getContact() {
        return Contact;
    }

    public void setContact(Long contact) {
        Contact = contact;
    }

    public ShopDetailsModal() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private Long Contact;

    public ShopDetailsModal(Long contact, String name,String accessToken) {
        Contact = contact;
        Name = name;
        accessToken = AccessToken;
    }

    private String Name;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }



    private String AccessToken;
}
