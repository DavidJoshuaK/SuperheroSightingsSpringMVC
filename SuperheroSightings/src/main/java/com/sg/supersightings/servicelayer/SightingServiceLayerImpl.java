/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public class SightingServiceLayerImpl implements SightingServiceLayer {

    private SightingDao sightingDao;
    
    public SightingServiceLayerImpl(SightingDao sightingDao){
        this.sightingDao = sightingDao;
    }
    
    @Override
    public void addSighting(Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        sightingDao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
       return sightingDao.getSightingById(id);
     }

    @Override
    public List<Sighting> getSightingsDescDate() {
        return sightingDao.getSightingsDescDate();
    }

    @Override
    public void insertSuperSighting(Super superperson, Sighting sighting) {
        sightingDao.insertSuperSighting(superperson, sighting);
    }

    @Override
    public List<Sighting> getSightingByLocationId(int locationId) {
        return sightingDao.getSightingByLocationId(locationId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }
    
    @Override
    public List<Sighting> findSightingForSuper(Super superperson){
        return sightingDao.findSightingForSuper(superperson);
    }
    
    @Override
    public List<Sighting> getRecentSightings(int id){
        List<Sighting> allSightings = sightingDao.getAllSightings();
        
        List<Sighting> recents = allSightings.subList(0, id);
        
        return recents;
    }   
    
}
