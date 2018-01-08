/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.servicelayer.OrganizationServiceLayer;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DavidKing
 */
public class OrganizationServiceTest {

    OrganizationServiceLayer service;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        service = ctx.getBean("OrganizationServiceLayer", OrganizationServiceLayer.class);

        List<Organization> organizations = service.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            service.deleteOrganization(currentOrganization.getOrganizationId());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetOrganization() {
        Organization o = new Organization();
        o.setName("Avengers");
        o.setDescription("Large");
        o.setType("Good");
        o.setAddress("201 Stree");
        o.setCity("New York");
        o.setState("NY");
        o.setZipcode("12000");
        o.setPhone("555-3030");
        service.addOrganization(o);

        Organization fromDao = service.getOrganizationById(o.getOrganizationId());
        assertEquals(fromDao, o);
    }

    @Test
    public void updateOrganization() {
        Organization o = new Organization();
        o.setName("Avengers");
        o.setDescription("Large");
        o.setType("Good");
        o.setAddress("201 Stree");
        o.setCity("New York");
        o.setState("NY");
        o.setZipcode("12000");
        o.setPhone("555-3030");
        service.addOrganization(o);

        o.setState("KY");
        service.updateOrganization(o);
        Organization fromDao = service.getOrganizationById(o.getOrganizationId());
        assertEquals(fromDao, o);
    }

    @Test
    public void deleteOrganization() {
        Organization o = new Organization();
        o.setName("Avengers");
        o.setDescription("Large");
        o.setType("Good");
        o.setAddress("201 Stree");
        o.setCity("New York");
        o.setState("NY");
        o.setZipcode("12000");
        o.setPhone("555-3030");
        service.addOrganization(o);

        Organization fromDao = service.getOrganizationById(o.getOrganizationId());
        assertEquals(fromDao, o);

        service.deleteOrganization(o.getOrganizationId());
        assertNull(service.getOrganizationById(o.getOrganizationId()));
    }

    @Test
    public void getAllOrganizations() {
        Organization o = new Organization();
        o.setName("Avengers");
        o.setDescription("Large");
        o.setType("Good");
        o.setAddress("201 Stree");
        o.setCity("New York");
        o.setState("NY");
        o.setZipcode("12000");
        o.setPhone("555-3030");
        service.addOrganization(o);

        Organization o2 = new Organization();
        o2.setName("X-Men");
        o2.setDescription("Mutants");
        o2.setType("Good");
        o2.setAddress("11th Street");
        o2.setCity("New York");
        o2.setState("NY");
        o2.setZipcode("12000");
        o2.setPhone("555-3030");
        service.addOrganization(o2);

        assertEquals(2, service.getAllOrganizations().size());

    }
}
