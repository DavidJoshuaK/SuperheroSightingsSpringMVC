/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public class LocationServiceLayerImpl implements LocationServiceLayer {

    private LocationDao locationDao;

    public LocationServiceLayerImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locationDao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        return locationDao.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location getLocationIdBySighting(Sighting sighting) {
        return locationDao.getLocationIdBySighting(sighting);
    }

}
