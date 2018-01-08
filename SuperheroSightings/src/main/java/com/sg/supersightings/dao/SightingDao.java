/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public interface SightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getSightingsDescDate();

    public void insertSuperSighting(Super superperson, Sighting sighting);

    public List<Sighting> getSightingByLocationId(int locationId);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> findSightingForSuper(Super superperson);

}
