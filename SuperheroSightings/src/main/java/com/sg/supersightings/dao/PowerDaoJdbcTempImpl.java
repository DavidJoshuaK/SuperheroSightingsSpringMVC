/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DavidKing
 */
public class PowerDaoJdbcTempImpl implements PowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_POWER
            = "insert into Power (Description) values (?)";

    private static final String SQL_DELETE_POWER
            = "delete from Power where PowerId = ?";

    private static final String SQL_UPDATE_POWER
            = "update Power set Description = ? where PowerId = ?";

    private static final String SQL_SELECT_POWER
            = "select * from Power where PowerId = ?";
    //all the powers for a given super
    private static final String SQL_SELECT_POWER_BY_SUPERID
            = "select p.PowerId, p.Description from Power p "
            + "join SuperPower sp on p.PowerId = sp.PowerId where "
            + "sp.SuperId = ?";

    private static final String SQL_SELECT_ALL_POWER
            = "select * from Power";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getDescription());
        int powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        power.setPowerId(powerId);
    }

    @Override
    public void deletePower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getDescription(),
                power.getPowerId());
    }

    @Override
    public Power getPowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER,
                    new PowerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWER, new PowerMapper());
    }

    @Override
    public List<Power> findPowerForSuper(Super superperson) {
        return jdbcTemplate.query(SQL_SELECT_POWER_BY_SUPERID,
                new PowerMapper(),
                superperson.getSuperId());
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerId(rs.getInt("powerId"));
            p.setDescription(rs.getString("description"));

            return p;
        }
    }
}
