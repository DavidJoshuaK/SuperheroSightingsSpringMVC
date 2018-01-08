/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public interface LocationDao {

    public void addLocation(Location location);

    public void deleteLocation(int locationId);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations();

    public Location getLocationIdBySighting(Sighting sighting);
}
