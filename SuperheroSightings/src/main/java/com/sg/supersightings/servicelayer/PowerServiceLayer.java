/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.servicelayer;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Super;
import java.util.List;

/**
 *
 * @author DavidKing
 */
public interface PowerServiceLayer {

    public void addPower(Power power);

    public void deletePower(int powerId);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();

    public List<Power> findPowerForSuper(Super superperson);
}
