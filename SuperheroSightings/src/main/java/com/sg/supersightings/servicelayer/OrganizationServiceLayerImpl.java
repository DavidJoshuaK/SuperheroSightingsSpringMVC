/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public class OrganizationServiceLayerImpl implements OrganizationServiceLayer {

    private OrganizationDao orgDao;
    
    public OrganizationServiceLayerImpl(OrganizationDao orgDao){
        this.orgDao = orgDao;
    }
    
    @Override
    public void addOrganization(Organization organization) {
        orgDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        orgDao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        orgDao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
       return orgDao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return orgDao.getAllOrganizations();
    }

    @Override
    public List<Organization> findOrganizationsForSuper(Super superperson) {
        return orgDao.findOrganizationsForSuper(superperson);
    }
    
}
