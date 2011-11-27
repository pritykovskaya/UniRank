package ru.unirank.core.mapper;


import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.RankingRawDescription;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:29:27
 */
public class RankingRawDescriptionMapper implements ParameterizedRowMapper<RankingRawDescription> {
    public final RankingRawDescription mapRow(final ResultSet resultSet, final int i) {
        try {
            return new RankingRawDescription(
                    resultSet.getInt("id"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            return new RankingRawDescription(0,"");
        }
    }
}

