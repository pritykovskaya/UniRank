package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:29:27
 */
public class FacultyMapper implements ParameterizedRowMapper<Faculty> {
    public final Faculty mapRow(final ResultSet resultSet, final int i) {
        try {
            return new Faculty(
                    resultSet.getInt("id"),
                    resultSet.getInt("university_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            return new Faculty(0,0, "","");
        }
    }
}

