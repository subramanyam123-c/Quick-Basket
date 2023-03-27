package com.example.model;

public class Shop {
    
    private String name;
    private ZipCode zipCode;
    private boolean status;
    
    public Shop() {
        
    }
    
    public Shop(String name, ZipCode zipCode, boolean status) {
        this.name = name;
        this.zipCode = zipCode;
        this.status = status;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
