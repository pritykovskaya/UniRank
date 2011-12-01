package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.TableOfFactsMapper;
import ru.unirank.core.tables.TableOfFacts;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 11:41:55
 */
public class TableOfFactsManager {

    private static final Logger log = Logger.getLogger(TableOfFactsManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private TableOfFactsMapper TableOfFactsMapper;

    public TableOfFactsManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.TableOfFactsMapper = new TableOfFactsMapper();
    }
    public TableOfFactsManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.TableOfFactsMapper = new TableOfFactsMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE TableOfFacts");
        }catch (DataAccessException ex)
        {
            log.error("error in cleaning Data");
        }
    }
    public int addTableOfFacts(int rank_id, int facult_id) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT idTableOfFacts from TableOfFacts", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
            DirectionManager direction = new DirectionManager(simplejdbcTemplate);
            FacultyManager faculty = new FacultyManager(simplejdbcTemplate);
            RankingMethodManager rankingMethod = new RankingMethodManager(simplejdbcTemplate);
            RankingResultManager rankingResult = new RankingResultManager(simplejdbcTemplate);
            RankingRawDescriptionManager rankingRawDescription = new RankingRawDescriptionManager(simplejdbcTemplate);
            SpecialityFacultyManager specialityFaculty = new SpecialityFacultyManager(simplejdbcTemplate);
            SpecialityManager speciality = new SpecialityManager(simplejdbcTemplate);
            UniversityManager university = new UniversityManager(simplejdbcTemplate);
            for(int speciality_id : specialityFaculty.findAllSpecialityIdByFacultyId(facult_id))
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO TableOfFacts (idTableOfFacts, direct_id, spec_id, facult_id, uni_id, city, method_id, rank_id, format_id, info_id) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    new Object[]{lastId, rankingMethod.getRankingMethodById(rankingResult.getRankingResultById(rank_id).getMethodId()).getDerectionId(),
                            speciality_id,
                            facult_id,
                            faculty.getFacultyById(facult_id).getUniversityId(),
                            university.getUniversityById(facult_id).getCity(),
                            rankingResult.getRankingResultById(rank_id).getMethodId(),
                            rank_id,
                            rankingMethod.getRankingMethodById(rankingResult.getRankingResultById(rank_id).getMethodId()).getRawDescriptionId(),
                            rankingResult.getRankingResultById(rank_id).getValue()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DOUBLE,});
             return lastId;
        } catch (DataAccessException e) {
                log.error("cannot add TableOfFacts");
        }
        return 0;
    }
       

     public List<TableOfFacts> getAllTable() {
        List<TableOfFacts> TableOfFactss =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM TableOfFacts", TableOfFactsMapper);
     return TableOfFactss;
    }

    public TableOfFacts getTableOfFactsById(int TableOfFacts_id) {
        List<TableOfFacts> Universities =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM TableOfFacts WHERE id = ?",
                        new Object[]{TableOfFacts_id},
                        new int[]{Types.INTEGER},
                        TableOfFactsMapper);
        if (Universities.size() > 0) {
            return Universities.get(0);
        }
        return null;
    }
    
}
