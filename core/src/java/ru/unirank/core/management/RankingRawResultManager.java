/*package ru.compscicenter.schoolinfo.storage.Management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.compscicenter.schoolinfo.storage.Mapper.RankingRawResultMapper;
import ru.compscicenter.schoolinfo.storage.Tables.RankingRawResult;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 11:50:50
 */      /*
public class RankingRawResultManager {


    private static final Logger log = Logger.getLogger(RankingRawResultManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private RankingRawResultMapper RankingRawResultMapper;

    public RankingRawResultManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.RankingRawResultMapper = new RankingRawResultMapper();
    }

    public RankingRawResultManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.RankingRawResultMapper = new RankingRawResultMapper();
    }

    public void cleanDataStore() {
        try {
            simplejdbcTemplate.update("TRUNCATE TABLE RankingRawResult");
        } catch (DataAccessException ex) {
            log.error("error in cleaning Data");
        }
    }

    public int addRankingRawResult(RankingRawResult rankingRawResult, String faculty) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from RankingRawResult", Integer.class);
            int lastId = 1;
            if (id.size() > 0) {
                lastId = (Integer) (id.get(id.size() - 1)) + 1;
            }                          
            FacultyManager facultet = new FacultyManager(simplejdbcTemplate);
            RankingRawDescriptionManager rank = new RankingRawDescriptionManager(simplejdbcTemplate);
            if (facultet.getFacultyIdByName(faculty) == 0 || !rank.exist(rankingRawResult.getRankingRawInfoDescriptionId())) {
                System.out.println("The foreign key does not exist");
                return 0;
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO RankingRawResult (id, faculty_id, result_id, ranking_raw_info_description_id, value) VALUES(?,?,?,?,?)",
                    new Object[]{lastId, facultet.getFacultyIdByName(faculty),rankingRawResult.getResultId(), rankingRawResult.getRankingRawInfoDescriptionId(), rankingRawResult.getValue()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER});
            return lastId;
        } catch (DataAccessException e) {
            System.out.println(e);
            //log.error("cannot add RankingRawResult");
        }
        return 0;
    }

    public void addListOfRankingRawResult(List<RankingRawResult> ranking , String faculty) {
        for (RankingRawResult rank : ranking) {
            addRankingRawResult(rank, faculty);
        }
    }
        public RankingRawResult getRankingRawResultById(int id){
        List <RankingRawResult> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingRawResult WHERE id=?",
                new Object[] {id},
                new int[]{Types.INTEGER},
                RankingRawResultMapper);
        return ranking.get(0);
    }

        public RankingRawResult getRankingRawResultByFacultyId(int faculty_id){
        List <RankingRawResult> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingRawResult WHERE faculty_id=?",
                new Object[] {faculty_id},
                new int[]{Types.INTEGER},
                RankingRawResultMapper);
        return ranking.get(0);
    }

    public List<RankingRawResult> getAllRankingRawResults() {
        List<RankingRawResult> RankingRawResult =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingRawResult", RankingRawResultMapper);
        return RankingRawResult;
    }

    public boolean exist(int id) {
        if (simplejdbcTemplate.getJdbcOperations().queryForInt("SELECT id FROM RankingRawResult WHERE id=?", new Object[]{id}) != 0)
            return true;
        return false;
    }

}
*/