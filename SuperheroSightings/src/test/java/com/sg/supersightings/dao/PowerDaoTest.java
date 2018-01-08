/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DavidKing
 */
public class PowerDaoTest {

    PowerDao dao;

    public PowerDaoTest() {
    }

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

        dao = ctx.getBean("PowerDao", PowerDao.class);

//        List<Super> supers = dao.getAllSupers();
//        for (Super currentSuper : supers) {
//            dao.deleteSuper(currentSuper.getSuperId());
//        }
//        
//        List<Sighting> sightings = dao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            dao.deleteSighting(currentSighting.getSightingId());
//        }
//        
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getPowerId());
        }
//        
//        List<Organization> organizations = dao.getAllOrganizations();
//        for (Organization currentOrganization : organizations) {
//            dao.deleteOrganization(currentOrganization.getOrganizationId());
//        }
//        
//        List<Location> locations = dao.getAllLocations();
//        for (Location currentLocation : locations) {
//            dao.deleteLocation(currentLocation.getLocationId());
//        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetPower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        dao.addPower(power);

        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void updatePower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        dao.addPower(power);
        power.setDescription("Fast");
        dao.updatePower(power);
        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        dao.addPower(power);
        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
        dao.deletePower(power.getPowerId());
        assertNull(dao.getPowerById(power.getPowerId()));

    }

    @Test
    public void getAllPowers() {
        Power power = new Power();
        power.setDescription("Very Strong");

        dao.addPower(power);

        Power power2 = new Power();
        power2.setDescription("Smart");

        dao.addPower(power2);

        assertEquals(2, dao.getAllPowers().size());
    }

}
