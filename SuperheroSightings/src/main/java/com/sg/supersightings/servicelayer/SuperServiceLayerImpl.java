/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.dao.SuperDao;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public class SuperServiceLayerImpl implements SuperServiceLayer {

    private SuperDao superDao;
    
    public SuperServiceLayerImpl(SuperDao superDao){
        this.superDao = superDao;
    }
    
    @Override
    public void addSuper(Super superperson) {
        superDao.addSuper(superperson);
    }

    @Override
    public void deleteSuper(int superId) {
        superDao.deleteSuper(superId);
    }

    @Override
    public void updateSuper(Super superperson) {
        superDao.updateSuper(superperson);
    }

    @Override
    public Super getSuperById(int id) {
        return superDao.getSuperById(id);
    }

    @Override
    public List<Super> getAllSupers() {
        return superDao.getAllSupers();
    }

    @Override
    public List<Super> getSuperBySighting(int sightingId) {
        return superDao.getSuperBySighting(sightingId);
    }

    @Override
    public List<Super> getSuperByPower(int powerId) {
        return superDao.getSuperByPower(powerId);
    }

    @Override
    public List<Super> getSuperByOrganization(int organizationId) {
        return superDao.getSuperByOrganization(organizationId);
    }

    @Override
    public List<Super> findSuperForOrganization(Organization organization) {
        return superDao.findSuperForOrganization(organization);
    }

    @Override
    public List<Super> findSuperForSighting(Sighting sighting) {
        return superDao.findSuperForSighting(sighting);
    }

    @Override
    public void insertSuperPower(Super superperson, Power power) {
        superDao.insertSuperPower(superperson, power);
    }

    @Override
    public void insertSuperOrganization(Super superperson, Organization organization) {
        superDao.insertSuperOrganization(superperson, organization);
    }
    
}
