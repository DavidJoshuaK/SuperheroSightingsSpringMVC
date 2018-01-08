/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DavidKing
 */
public class Sighting {

    private int sightingId;
   
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private Location location;
    private List<Super> supers;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }
   // @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.sightingId;
        hash = 23 * hash + Objects.hashCode(this.date);
        hash = 23 * hash + Objects.hashCode(this.location);
        hash = 23 * hash + Objects.hashCode(this.supers);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        return true;
    }

}
