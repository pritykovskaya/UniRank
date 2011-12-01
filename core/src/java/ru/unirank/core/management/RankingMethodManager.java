package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.RankingMethodMapper;
import ru.unirank.core.tables.RankingMethod;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 18.11.2011
 * Time: 11:50:50
 */
public class RankingMethodManager {
    

    private static final Logger log = Logger.getLogger(RankingMethodManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private RankingMethodMapper rankingMethodMapper;

    public RankingMethodManager (DataSource dataSource){
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.rankingMethodMapper = new RankingMethodMapper();
    }
    public RankingMethodManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.rankingMethodMapper = new RankingMethodMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE RankingMethod");
        }catch (DataAccessException ex)
        {
              log.error("error in cleaning Data");
        }
    }
   public int addRankingMethod(RankingMethod RankingMethod, String name) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from RankingMethod", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
            DirectionManager direct = new DirectionManager(simplejdbcTemplate);
            if(direct.getDirectionIdByName(name) == 0){
                log.error("The foreign key does not exist");
                return 0;
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO RankingMethod (id, direction_id, raw_description_id, coeff, implement_class) VALUES(?,?,?,?,?)",
                    new Object[]{lastId, direct.getDirectionIdByName(name), RankingMethod.getRawDescriptionId(), RankingMethod.getCoeff(), RankingMethod.getImplementClass()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR});
             return lastId;
        } catch (DataAccessException e) {
                log.error("cannot add RankingMethod");
        }
        return 0;
    }
       
        public void addListOfRankingMethod (List<RankingMethod> ranking, String name) {
        for (RankingMethod rank : ranking) {
            addRankingMethod(rank, name);
        }
    }
     public List<RankingMethod> getAllRankingMethods() {
        List<RankingMethod> rankingmethons =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingMethod", rankingMethodMapper);
     return rankingmethons;
    }
    
        public RankingMethod getRankingMethodById(int id){
        List <RankingMethod> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM RankingMethod WHERE id=?",
                new Object[] {id},
                new int[]{Types.INTEGER},
                rankingMethodMapper);
        return ranking.get(0);
    }

    public boolean exist(int id){
        if(simplejdbcTemplate.getJdbcOperations().queryForInt("SELECT id FROM RankingMethod WHERE id=?", new Object[]{id}) != 0)
            return true;
        return false;
    }

}
