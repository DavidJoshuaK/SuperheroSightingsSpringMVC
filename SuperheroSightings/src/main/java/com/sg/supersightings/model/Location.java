/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author DavidKing
 */
public class Location {

    private int locationId;
    @NotEmpty(message = "You must supply a value for name.")
    @Length(max = 60, message = "Name must be no more than 60 characters in length.")
    private String name;
    @NotEmpty(message = "You must supply a value for description.")
    @Length(max = 100, message = "Description must be no more than 100 characters in length.")
    private String description;
    @NotEmpty(message = "You must supply a value for address.")
    @Length(max = 60, message = "Address must be no more than 60 characters in length.")
    private String address;
    @NotEmpty(message = "You must supply a value for city.")
    @Length(max = 60, message = "City must be no more than 60 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a value for state.")
    @Length(max = 40, message = "State must be no more than 40 characters in length.")
    private String state;
    @NotEmpty(message = "You must supply a value for zip code.")
    @Length(max = 11, message = "Zip code must be no more than 11 characters in length.")
    private String zipcode;
    @NotEmpty(message = "You must supply a value for latitude.")
    @Digits(integer = 2, fraction = 6, message = "Must have it in the format of 00.000000")
    private BigDecimal latitude;
    @NotEmpty(message = "You must supply a value for longitude.")
    @Digits(integer = 2, fraction = 6, message = "Must have it in the format of 00.000000")
    private BigDecimal longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.locationId;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.city);
        hash = 29 * hash + Objects.hashCode(this.state);
        hash = 29 * hash + Objects.hashCode(this.zipcode);
        hash = 29 * hash + Objects.hashCode(this.latitude);
        hash = 29 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }

        return true;
    }

}
