package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.University;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 15:19:26
 */
public class UniversityMapper  implements ParameterizedRowMapper<University> {
    public final University mapRow(final ResultSet resultSet, final int i) {
        try {
            return new University(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("city"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            return new University(0,"", "", "");
        }
    }
}

