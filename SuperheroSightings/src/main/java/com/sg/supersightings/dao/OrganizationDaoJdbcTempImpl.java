/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
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
public class OrganizationDaoJdbcTempImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ORGANIZATION PREPARED STATEMENTS
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization (Name, Description, Type, Address, "
            + "City, State, Zipcode, Phone) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set Name = ?, Description = ?, Type = ?, "
            + "Address = ?, City = ?, State = ?, Zipcode = ?, Phone = ? where "
            + "OrganizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from Organization where OrganizationId = ?";

    //all organizations for a super
    private static final String SQL_SELECT_ORGANIZATION_BY_SUPERID
            = "select o.OrganizationId, o.Name, o.Description, o.Type, o.Address, o.City, "
            + "o.State, o.Zipcode, o.Phone from Organization o join "
            + "SuperOrganization so on o.OrganizationId = so.OrganizationId where "
            + "so.SuperId = ?";

    private static final String SQL_DELETE_SUPERORGANIZATION_FK
            = "delete from SuperOrganization where OrganizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATION
            = "select * from Organization";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getType(),
                organization.getAddress(),
                organization.getCity(),
                organization.getState(),
                organization.getZipcode(),
                organization.getPhone());

        int organizationId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);
        organization.setOrganizationId(organizationId);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_SUPERORGANIZATION_FK, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getType(),
                organization.getAddress(),
                organization.getCity(),
                organization.getState(),
                organization.getZipcode(),
                organization.getPhone(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION,
                new OrganizationMapper());
    }

    @Override
    public List<Organization> findOrganizationsForSuper(Super superperson) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_SUPERID,
                new OrganizationMapper(),
                superperson.getSuperId());
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
