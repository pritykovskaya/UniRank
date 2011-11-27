package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 13.11.2011
 * Time: 16:48:52
 */
public class SpecialityMapper implements ParameterizedRowMapper<Speciality> {
    public final Speciality mapRow(final ResultSet resultSet, final int i) {
        try {
            return new Speciality(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("direction_id"));
        } catch (SQLException e) {
            return new Speciality(0,"",0);
        }
    } 
}
