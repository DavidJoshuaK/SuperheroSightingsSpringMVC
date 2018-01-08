/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author DavidKing
 */
public class Organization {
    
    private int organizationId;
    @NotEmpty(message = "You must supply a value for name.")
    @Length(max = 60, message = "Name must be no more than 60 characters in length.")
    private String name;
    @NotEmpty(message = "You must supply a value for description.")
    @Length(max = 100, message = "Description must be no more than 100 characters in length.")
    private String description;
    @NotEmpty(message = "You must supply a value for type (hero or villains?).")
    @Length(max = 7, message = "Type must be no more than 7 characters in length.")
    private String type;
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
    @NotEmpty(message = "You must supply a value for phone.")
    @Length(max = 20, message = "Phone must be no more than 20 characters in length.")
    private String phone;
    private List<Super> supers;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.organizationId;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.state);
        hash = 37 * hash + Objects.hashCode(this.zipcode);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.supers);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
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
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        return true;
    }

}
