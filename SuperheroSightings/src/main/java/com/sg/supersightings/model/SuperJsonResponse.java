/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.Map;

/**
 *
 * @author DavidKing
 */
public class SuperJsonResponse {

    private Super supers;
    private boolean validated;
    private Map<String, String> errorMessages;

    public Super getSupers() {
        return supers;
    }

    public void setSupers(Super supers) {
        this.supers = supers;
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
