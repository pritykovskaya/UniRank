package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.Direction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:23:40
 */
public class DirectionMapper implements ParameterizedRowMapper<Direction> {

    public final Direction mapRow(final ResultSet resultSet, final int i) {
        try {
            return new Direction(
                    resultSet.getInt("id"),
                    resultSet.getString("name"));
        } catch (SQLException e) {
            return new Direction(0, "");
        }
    }
}