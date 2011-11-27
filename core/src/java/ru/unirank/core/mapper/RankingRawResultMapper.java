/*package ru.compscicenter.schoolinfo.storage.Mapper;


import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.compscicenter.schoolinfo.storage.Tables.RankingRawResult;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:29:27
 *//*
public class RankingRawResultMapper implements ParameterizedRowMapper<RankingRawResult> {
    public final RankingRawResult mapRow(final ResultSet resultSet, final int i) {
        try {
            return new RankingRawResult(
                    resultSet.getInt("id"),
                    resultSet.getInt("faculty_id"),
                    resultSet.getInt("ranking_raw_info_description_id"),
                    resultSet.getDouble("value"));
        } catch (SQLException e) {
            return new RankingRawResult(0,0,0,0);
        }
    }
}

*/