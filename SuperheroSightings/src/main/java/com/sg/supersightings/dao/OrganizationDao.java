/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public interface OrganizationDao {

    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();

    public List<Organization> findOrganizationsForSuper(Super superperson);

}
