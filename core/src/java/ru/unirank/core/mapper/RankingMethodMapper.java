package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.RankingMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:29:27
 */
public class RankingMethodMapper implements ParameterizedRowMapper<RankingMethod> {
    public final RankingMethod mapRow(final ResultSet resultSet, final int i) {
        try {
            return new RankingMethod(
                    resultSet.getInt("id"),
                    resultSet.getInt("direction_id"),
                    resultSet.getInt("raw_description_id"),
                    resultSet.getInt("coeff"),
                    resultSet.getString("implement_class"));
        } catch (SQLException e) {
            return new RankingMethod(0,0,0,0,"");
        }
    }
}