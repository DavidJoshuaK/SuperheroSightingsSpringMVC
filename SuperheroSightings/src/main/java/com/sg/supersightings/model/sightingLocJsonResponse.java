package com.sg.supersightings.model;


import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DavidKing
 */
public class sightingLocJsonResponse {
    
    private SightingLoc sl;
    private boolean validated;
    private Map<String, String> errorMessages;

    public SightingLoc getSl() {
        return sl;
    }

    public void setSl(SightingLoc sl) {
        this.sl = sl;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
    
}
