package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.SpecialityFaculty;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 13.11.2011
 * Time: 17:04:08
 */
public class SpecialityFacultyMapper  implements ParameterizedRowMapper<SpecialityFaculty> {
    public final SpecialityFaculty mapRow(final ResultSet resultSet, final int i) {
        try {
            return new SpecialityFaculty(
                    resultSet.getInt("speciality_id"),
                    resultSet.getInt("faculty_id"));
        } catch (SQLException e) {
            return new SpecialityFaculty(0,0);
        }
    } 
}
