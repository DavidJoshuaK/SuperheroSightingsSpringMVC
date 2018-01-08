/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SuperDao;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public class PowerServiceLayerImpl implements PowerServiceLayer{

    private PowerDao powerDao;
    
    public PowerServiceLayerImpl(PowerDao powerDao){
        this.powerDao = powerDao;
    }
    
    @Override
    public void addPower(Power power) {
        powerDao.addPower(power);
    }

    @Override
    public void deletePower(int powerId) {
        powerDao.deletePower(powerId);
    }

    @Override
    public void updatePower(Power power) {
        powerDao.updatePower(power);
    }

    @Override
    public Power getPowerById(int id) {
        return powerDao.getPowerById(id);
    }

    @Override
    public List<Power> getAllPowers() {
        return powerDao.getAllPowers();
    }

    @Override
    public List<Power> findPowerForSuper(Super superperson) {
        return powerDao.findPowerForSuper(superperson);
    }
    
}
