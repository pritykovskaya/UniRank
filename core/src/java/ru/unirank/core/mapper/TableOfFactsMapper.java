package ru.unirank.core.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.unirank.core.tables.TableOfFacts;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 10:32:49
 */
public class TableOfFactsMapper implements ParameterizedRowMapper<TableOfFacts> {
        public final TableOfFacts mapRow(final ResultSet resultSet, final int i) {
            try {
                return new TableOfFacts(
                        resultSet.getInt("id"),
                        resultSet.getInt(" direction_id"),
                        resultSet.getInt("speciality_id"),
                        resultSet.getInt("faculty_id"),
                        resultSet.getInt(" university_id"),
                        resultSet.getString("String city"),
                        resultSet.getInt("method_id"),
                        resultSet.getInt("rank_id"),
                        resultSet.getInt("format_id"),
                        resultSet.getInt("info_id"));
            } catch (SQLException e) {
                return new TableOfFacts(0,0,0,0,0,"",0,0,0,0);
            }
        }
    }

