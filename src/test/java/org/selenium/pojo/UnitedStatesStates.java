package org.selenium.pojo;

import org.selenium.util.JacksonUtils;

import java.io.IOException;

public class UnitedStatesStates {

    private String firstName;
    private String lastName;
    private String country;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String email;

    public UnitedStatesStates(){};

    public UnitedStatesStates(String state) throws IOException {
        UnitedStatesStates[] address = JacksonUtils.deserializeJson2("state.json",UnitedStatesStates[].class);
        boolean stateFound = false;
        for (UnitedStatesStates uncleSam : address) {
            if (uncleSam.getState().equals(state)) {
                this.state = state;
                this.firstName = uncleSam.getFirstName();
                this.lastName = uncleSam.getLastName();
                this.country = uncleSam.getCountry();
                this.streetAddress = uncleSam.getStreetAddress();
                this.city = uncleSam.getCity();
                this.zipCode = uncleSam.getZipCode();
                this.email = uncleSam.getEmail();
                stateFound = true;
                break; // Exit loop once state is found
            }
        }
        if (!stateFound) {
            throw new IllegalArgumentException("State not found in JSON: " + state);
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountryName(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
