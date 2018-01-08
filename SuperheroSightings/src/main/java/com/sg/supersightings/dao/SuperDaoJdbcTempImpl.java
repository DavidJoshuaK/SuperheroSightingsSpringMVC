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
public class SuperDaoJdbcTempImpl implements SuperDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//SUPER PREPARED STATEMENTS
    private static final String SQL_INSERT_SUPER
            = "insert into Super (Name, Description) values (?, ?)";

    private static final String SQL_INSERT_SUPERPOWER
            = "insert into SuperPower (SuperId, PowerId) values (?, ?)";

    private static final String SQL_INSERT_SUPERSIGHTING
            = "insert into SuperSighting (SuperId, SightingId) values (?, ?)";

    private static final String SQL_INSERT_SUPERORGANIZATION
            = "insert into SuperOrganization (SuperId, OrganizationId) values(?, ?)";

    private static final String SQL_DELETE_SUPER
            = "delete from Super where SuperId= ?";

    private static final String SQL_DELETE_SUPERSIGHTING
            = "delete from SuperSighting where SuperId = ?";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from SuperPower where SuperId=?";

    private static final String SQL_DELETE_SUPERORGANIZATION
            = "delete from SuperOrganization where SuperId = ?";

    private static final String SQL_DELETE_SUPERORGANIZATION_FK
            = "delete from SuperOrganization where OrganizationId = ?";

    private static final String SQL_UPDATE_SUPER
            = "update Super set Name=?, Description=? where SuperId= ?";

    private static final String SQL_SELECT_SUPER
            = "select * from Super where SuperId= ?";

    //Find all the supers by a given sighting
    private static final String SQL_SELECT_SUPER_BY_SIGHTINGID
            = "select su.SuperId, su.Name, su.Description from Super su "
            + "join SuperSighting ss on su.SuperId = ss.SuperId where "
            + "ss.SightingId = ?";

    private static final String SQL_SELECT_SUPER_BY_SIGHTING_ID
            = "select s.SuperId, s.Name, s.Description from Super s join SuperSighting ss "
            + "on SightingId where s.SuperId = ss.SuperId "
            + "and ss.SightingId = ?";
    //all supers at an organization
    private static final String SQL_SELECT_SUPER_BY_ORGANIZATIONID
            = "select su.SuperId, su.Name, su.Description from Super su "
            + "join SuperOrganization so on su.SuperId = so.SuperId where "
            + "so.OrganizationId = ?";
    //all supers with a given power
    private static final String SQL_SELECT_SUPER_BY_POWERID
            = "select su.SuperId, su.Name, su.Description from Super su "
            + "join SuperPower sp on su.SuperId = sp.SuperId where "
            + "sp.PowerId = ?";

    private static final String SQL_SELECT_ALL_SUPER
            = "select * from Super";

    private static final String SQL_SELECT_SIGHTING_BY_SUPERID
            = "select s.SightingId, s.LocationId, s.Date from "
            + "Sighting s join SuperSighting ss on SuperId "
            + "where s.SightingId = ss.SightingId "
            + "and ss.SuperId = ?";

    private static final String SQL_SELECT_POWER_BY_SUPERID
            = "select p.PowerId, p.Description from Power p "
            + "join SuperPower sp on p.PowerId = sp.PowerId where "
            + "sp.SuperId = ?";

    private static final String SQL_SELECT_ORGANIZATION_BY_SUPERID
            = "select o.OrganizationId, o.Name, o.Description, o.Type, o.Address, o.City, "
            + "o.State, o.Zipcode, o.Phone from Organization o join "
            + "SuperOrganization so on o.OrganizationId = so.OrganizationId where "
            + "so.SuperId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuper(Super superperson) {

        jdbcTemplate.update(SQL_INSERT_SUPER,
                superperson.getName(),
                superperson.getDescription());
        superperson.setSuperId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        insertSuperLists(superperson);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuper(int superId) {
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING, superId);
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superId);
        jdbcTemplate.update(SQL_DELETE_SUPERORGANIZATION, superId);
        jdbcTemplate.update(SQL_DELETE_SUPER, superId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuper(Super superperson) {
        jdbcTemplate.update(SQL_UPDATE_SUPER,
                superperson.getName(),
                superperson.getDescription(),
                superperson.getSuperId());
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING, superperson.getSuperId());
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, superperson.getSuperId());
        jdbcTemplate.update(SQL_DELETE_SUPERORGANIZATION, superperson.getSuperId());

        insertSuperLists(superperson);
    }

    @Override
    public Super getSuperById(int id) {
        try {
            Super superperson = jdbcTemplate.queryForObject(SQL_SELECT_SUPER,
                    new SuperMapper(), id);
            if (superperson.getPowers() != null) {
                superperson.setPowers(findPowerForSuper(superperson));
            }
            if (superperson.getSightings() != null) {
                superperson.setSightings(findSightingForSuper(superperson));
            }
            if (superperson.getOrganizations() != null) {
                superperson.setOrganizations(findOrganizationsForSuper(superperson));
            }
            return superperson;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Super> getAllSupers() {
        List<Super> superList = jdbcTemplate.query(SQL_SELECT_ALL_SUPER,
                new SuperMapper());

        return associateTablesWithSupers(superList);
    }

    @Override
    public List<Super> getSuperBySighting(int sightingId) {
        List<Super> superList
                = jdbcTemplate.query(SQL_SELECT_SUPER_BY_SIGHTINGID,
                        new SuperMapper(),
                        sightingId);

        return associateTablesWithSupers(superList);

    }

    @Override
    public List<Super> getSuperByPower(int powerId) {
        List<Super> superList
                = jdbcTemplate.query(SQL_SELECT_SUPER_BY_POWERID,
                        new SuperMapper(),
                        powerId);

        return associateTablesWithSupers(superList);
    }

    @Override
    public List<Super> getSuperByOrganization(int organizationId) {
        List<Super> superList
                = jdbcTemplate.query(SQL_SELECT_SUPER_BY_ORGANIZATIONID,
                        new SuperMapper(),
                        organizationId);

        return associateTablesWithSupers(superList);
    }

    @Override
    public List<Super> findSuperForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_SUPER_BY_SIGHTING_ID,
                new SuperMapper(),
                sighting.getSightingId());
    }

    @Override
    public List<Super> findSuperForOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_SUPER_BY_ORGANIZATIONID,
                new SuperMapper(),
                organization.getOrganizationId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertSuperPower(Super superperson, Power power) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superperson.getSuperId(),
                power.getPowerId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertSuperOrganization(Super superperson, Organization organization) {
        jdbcTemplate.update(SQL_INSERT_SUPERORGANIZATION,
                superperson.getSuperId(),
                organization.getOrganizationId());
    }

//***********
//HELPER 
//***********
    private List<Super> associateTablesWithSupers(List<Super> superList) {
        for (Super currentSuper : superList) {
            currentSuper.setPowers(findPowerForSuper(currentSuper));
            currentSuper.setSightings(findSightingForSuper(currentSuper));
            currentSuper.setOrganizations(findOrganizationsForSuper(currentSuper));
        }

        return superList;

    }

    public List<Organization> findOrganizationsForSuper(Super superperson) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_SUPERID,
                new OrganizationMapper(),
                superperson.getSuperId());
    }

    public List<Power> findPowerForSuper(Super superperson) {
        return jdbcTemplate.query(SQL_SELECT_POWER_BY_SUPERID,
                new PowerMapper(),
                superperson.getSuperId());
    }

    public List<Sighting> findSightingForSuper(Super superperson) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_SUPERID,
                new SightingMapper(),
                superperson.getSuperId());
    }

    private void insertSuperLists(Super superperson) {
        final int superId = superperson.getSuperId();
        final List<Power> power = superperson.getPowers();
        final List<Sighting> sighting = superperson.getSightings();
        final List<Organization> organizations = superperson.getOrganizations();

        //insert into Suppower table with an entry for each power of this superhero.
        if (power != null) {
            for (Power currentPower : power) {
                jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                        superId,
                        currentPower.getPowerId());
            }
        }
        if (sighting != null) {
            for (Sighting currentSighting : sighting) {
                jdbcTemplate.update(SQL_INSERT_SUPERSIGHTING,
                        superId,
                        currentSighting.getSightingId());
            }
        }
        if (organizations != null) {
            for (Organization currentOrganization : organizations) {
                jdbcTemplate.update(SQL_INSERT_SUPERORGANIZATION,
                        superId,
                        currentOrganization.getOrganizationId());
            }
        }
    }

//***********
//MAPPER
//***********    
    private static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int i) throws SQLException {
            Super su = new Super();
            su.setSuperId(rs.getInt("superId"));
            su.setName(rs.getString("name"));
            su.setDescription(rs.getString("description"));
            return su;
        }
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sightingId"));
            s.setDate(rs.getTimestamp("date"));
            return s;
        }
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

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization or = new Organization();
            or.setOrganizationId(rs.getInt("organizationId"));
            or.setName(rs.getString("name"));
            or.setDescription(rs.getString("description"));
            or.setType(rs.getString("type"));
            or.setAddress(rs.getString("address"));
            or.setCity(rs.getString("city"));
            or.setState(rs.getString("state"));
            or.setZipcode(rs.getString("zipcode"));
            or.setPhone(rs.getString("phone"));
            return or;
        }
    }

}
