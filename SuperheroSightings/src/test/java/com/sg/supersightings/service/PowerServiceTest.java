/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.servicelayer.PowerServiceLayer;
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
public class PowerServiceTest {

    PowerServiceLayer service;

    public PowerServiceTest() {
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

        service = ctx.getBean("PowerServiceLayer", PowerServiceLayer.class);

        List<Power> powers = service.getAllPowers();
        for (Power currentPower : powers) {
            service.deletePower(currentPower.getPowerId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetPower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        service.addPower(power);

        Power fromDao = service.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void updatePower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        service.addPower(power);
        power.setDescription("Fast");
        service.updatePower(power);
        Power fromDao = service.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setDescription("Very Strong");

        service.addPower(power);
        Power fromDao = service.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
        service.deletePower(power.getPowerId());
        assertNull(service.getPowerById(power.getPowerId()));

    }

    @Test
    public void getAllPowers() {
        Power power = new Power();
        power.setDescription("Very Strong");

        service.addPower(power);

        Power power2 = new Power();
        power2.setDescription("Smart");

        service.addPower(power2);

        assertEquals(2, service.getAllPowers().size());
    }

}
