package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.RankingRawDescriptionMapper;
import ru.unirank.core.tables.RankingRawDescription;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 11:50:50
 */
public class RankingRawDescriptionManager {


    private static final Logger log = Logger.getLogger(RankingRawDescriptionManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private RankingRawDescriptionMapper RankingRawDescriptionMapper;

    public RankingRawDescriptionManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.RankingRawDescriptionMapper = new RankingRawDescriptionMapper();
    }

    public RankingRawDescriptionManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.RankingRawDescriptionMapper = new RankingRawDescriptionMapper();
    }

    public void cleanDataStore() {
        try {
            simplejdbcTemplate.update("TRUNCATE TABLE RankingRawDescription");
        } catch (DataAccessException ex) {
            log.error("error in cleaning Data");
        }
    }

    public int addRankingRawDescription(RankingRawDescription rankingRawDescription) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from RankingRawInfoDescription", Integer.class);
            int lastId = 1;
            if (id.size() > 0) {
                lastId = (Integer) (id.get(id.size() - 1)) + 1;
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO RankingRawInfoDescription (id, description) VALUES(?,?)",
                    new Object[]{lastId, rankingRawDescription.getDescription()},
                    new int[]{Types.INTEGER, Types.VARCHAR});
            return lastId;
        } catch (DataAccessException e) {
            log.error("cannot add RankingRawDescription");
        }
        return 0;
    }

    public void addListOfRankingRawDescription(List<RankingRawDescription> ranking) {
        for (RankingRawDescription rank : ranking) {
            addRankingRawDescription(rank);
        }
    }
        public RankingRawDescription getRankingRawDescriptionById(int id){
        List <RankingRawDescription> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingRawDescription WHERE id=?",
                new Object[] {id},
                new int[]{Types.INTEGER},
                RankingRawDescriptionMapper);
        return ranking.get(0);
    }

    public List<RankingRawDescription> getAllRankingRawDescriptions() {
        List<RankingRawDescription> RankingRawDescription =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingRawDescription", RankingRawDescriptionMapper);
        return RankingRawDescription;
    }

    public boolean exist(int id) {
        if (simplejdbcTemplate.getJdbcOperations().queryForInt("SELECT id FROM RankingRawDescription WHERE id=?", new Object[]{id}) != 0)
            return true;
        return false;
    }

}
