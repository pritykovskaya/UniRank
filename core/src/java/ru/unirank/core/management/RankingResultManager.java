package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.RankingResultMapper;
import ru.unirank.core.tables.Faculty;
import ru.unirank.core.tables.RankingResult;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 11:50:50
 */
public class RankingResultManager {


    private static final Logger log = Logger.getLogger(RankingResultManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private RankingResultMapper rankingResultMapper;

    public RankingResultManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.rankingResultMapper = new RankingResultMapper();
    }

    public RankingResultManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.rankingResultMapper = new RankingResultMapper();
    }

    public void cleanDataStore() {
        try {
            simplejdbcTemplate.update("TRUNCATE TABLE RankingResult");
        } catch (DataAccessException ex) {
            log.error("error in cleaning Data");
        }
    }

    public int addRankingResult(RankingResult RankingResult, int method, String faculty_name, String university_name) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from RankingResult", Integer.class);
            int lastId = 1;
            if (id.size() > 0) {
                lastId = (Integer) (id.get(id.size() - 1)) + 1;
            }
            RankingMethodManager methodic = new RankingMethodManager(simplejdbcTemplate);
            FacultyManager faculty = new FacultyManager(simplejdbcTemplate);
            UniversityManager university = new UniversityManager(simplejdbcTemplate);
            TableOfFactsManager tableOfFacts = new TableOfFactsManager(simplejdbcTemplate);
            if (faculty.getFacultyIdByName(faculty_name) == 0 || !methodic.exist(method)) {
                log.error("The foreign key does not exist");
                return 0;
            }
            int facultet = 0;
            for(Faculty facult : faculty.getListOfFacultyByFacultyName(faculty_name)){
                if (facult.getUniversityId() == university.getUniversityIdByName(university_name))
                    facultet = facult.getId();
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO RankingResult (id, method_id, faculty_id, ranking_raw_info_description_id, rank, value) VALUES(?,?,?,?,?,?)",
                    new Object[]{lastId, method, facultet, methodic.getRankingMethodById(method).getRawDescriptionId(), RankingResult.getRank(), RankingResult.getValue()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE});
            tableOfFacts.addTableOfFacts(lastId,facultet);
            return lastId;
        } catch (DataAccessException e) {
            log.error("cannot add RankingResult");
        }
        return 0;
    }

    public void addListOfRankingResult(List<RankingResult> ranking, int method, String faculty, String university) {
        for (RankingResult rank : ranking) {
            addRankingResult(rank, method, faculty, university);
        }
    }

    public List<RankingResult> getAllRankingResults() {
        List<RankingResult> rankingresult =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingResult", rankingResultMapper);
        return rankingresult;
    }

    public boolean exist(int id) {
        if (simplejdbcTemplate.getJdbcOperations().queryForInt("SELECT id FROM RankingResult WHERE id=?", new Object[]{id}, new int[]{Types.INTEGER}) != 0)
            return true;
        return false;
    }
    
    public RankingResult getRankingResultById(int id){
        List <RankingResult> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingResult WHERE id=?",
                new Object[] {id},
                new int[]{Types.INTEGER},
                rankingResultMapper);
        return ranking.get(0);
    }

}
