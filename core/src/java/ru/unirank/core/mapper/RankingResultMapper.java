package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.RankingResult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:29:27
 */
public class RankingResultMapper implements ParameterizedRowMapper<RankingResult> {
    public final RankingResult mapRow(final ResultSet resultSet, final int i) {
        try {
            return new RankingResult(
                    resultSet.getInt("id"),
                    resultSet.getInt("method_id"),
                    resultSet.getInt("faculty_id"),
                    resultSet.getInt("ranking_raw_info_description_id"),
                    resultSet.getInt("rank"),
                    resultSet.getDouble("value"));
        } catch (SQLException e) {
            return new RankingResult(0,0,0,0,0,0);
        }
    }
}