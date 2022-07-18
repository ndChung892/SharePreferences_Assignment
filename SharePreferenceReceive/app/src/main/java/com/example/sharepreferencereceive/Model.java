package com.example.sharepreferencereceive;

import java.io.Serializable;

public class Model implements Serializable {
    private String name;
    private String Address;
    private String email;


    public Model() {
    }

    public Model(String name, String address, String email) {
        this.name = name;
        Address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", Address='" + Address + '\'' +
                ", email='" + email + '\'' +
                '}'+'\n';
    }
}
