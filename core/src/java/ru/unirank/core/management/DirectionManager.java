package ru.unirank.core.management;

import org.apache.log4j.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.DirectionMapper;
import ru.unirank.core.tables.Direction;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
  

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:48:44
 */
public class DirectionManager {

    private static final Logger log = Logger.getLogger(DirectionManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private DirectionMapper DirectionMapper;

    public DirectionManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.DirectionMapper = new DirectionMapper();
    }
    public DirectionManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.DirectionMapper = new DirectionMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE Direction");
        }catch (DataAccessException ex)
        {
            log.error("error in cleaning Data");
        }
    }
    public int addDirection(Direction direction) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from Direction", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
             if ( this.getDirectionIdByName(direction.getName()) > 0 ){
                 log.error("Such Direction already exist");
                 return this.getDirectionIdByName(direction.getName());
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO Direction (id, name) VALUES(?,?)",
                    new Object[]{lastId, direction.getName()},
                    new int[]{Types.INTEGER, Types.VARCHAR});
             return lastId;
        } catch (DataAccessException e) {
                log.error("cannot add direction");
        }
        return 0;
    }

        public void addListOfDirection (List<Direction> directions) {
        for (Direction dir : directions) {
            addDirection(dir);
        }
    }

     public List<Direction> getAllDirections() {
        List<Direction> directions =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Direction", DirectionMapper);
     return directions;
    }

    public Direction getDirectionById(int direction_id) {
        List<Direction> directions =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Direction WHERE id = ?",
                        new Object[]{direction_id},
                        new int[]{Types.INTEGER},
                        DirectionMapper);
        if (directions.size() > 0) {
            return directions.get(0);
        }
        return null;
    }

    public int getDirectionIdByName(String name) {
        List <Direction> univers = simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Direction WHERE name = ?",
                   new Object[]{name},
                   new int[]{Types.VARCHAR},
                   DirectionMapper);
        if (univers.size() == 0){
            return 0;
        }
        return univers.get(0).getId();
    }
    
}
