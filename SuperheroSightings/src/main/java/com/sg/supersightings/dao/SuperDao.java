/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public interface SuperDao {

    public void addSuper(Super superperson);

    public void deleteSuper(int superId);

    public void updateSuper(Super superperson);

    public Super getSuperById(int id);

    public List<Super> getAllSupers();
    
    public List<Super> getSuperBySighting(int sightingId);
    
    public List<Super> getSuperByPower(int powerId);
    
    public List<Super> getSuperByOrganization(int organizationId);
    
    public List<Super> findSuperForOrganization(Organization organization);
    
    public List<Super> findSuperForSighting(Sighting sighting);
    
    public void insertSuperPower(Super superperson, Power power);
    
    public void insertSuperOrganization(Super superperson, Organization organization);
    
}
