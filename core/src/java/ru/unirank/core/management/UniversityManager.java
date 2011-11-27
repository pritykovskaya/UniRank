package ru.unirank.core.management;


import org.apache.log4j.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.UniversityMapper;
import ru.unirank.core.tables.University;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:43:48
 */
public class UniversityManager {

    private static final Logger log = Logger.getLogger(UniversityManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private UniversityMapper UniversityMapper;

    public UniversityManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.UniversityMapper = new UniversityMapper();
    }
    public UniversityManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.UniversityMapper = new UniversityMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE University");
        }catch (DataAccessException ex)
        {
            System.out.println("something wrong" + ex);          
            //log.error("error in cleaning Data");
        }
    }
    public int addUniversity(University university) {
        try {
           // log.error("cannot add University");
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from University", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
            if ( this.getUniversityIdByName(university.getName()) > 0 ){
                System.out.println("Such University already exist");
                return this.getUniversityIdByName(university.getName());
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO University (id, name, city, description) VALUES(?,?,?,?)",
                    new Object[]{lastId, university.getName(), university.getCity(), university.getDescription()},
                    new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
             return lastId;
        } catch (DataAccessException e) {
                //System.out.println("something wrong");
                log.error("cannot add University");
        }
        return 0;
    }
       
        public void addListOfUniversity (List<University> Universities) {
        for (University dir : Universities) {
            addUniversity(dir);
        }
    }

     public List<University> getAllUniversities() {
        List<University> Universitys =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM University", UniversityMapper);
     return Universitys;
    }

    public University getUniversityById(int University_id) {
        List<University> Universities =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM University WHERE id = ?",
                        new Object[]{University_id},
                        new int[]{Types.INTEGER},
                        UniversityMapper);
        if (Universities.size() > 0) {
            return Universities.get(0);
        }
        return null;
    }

    public int getUniversityIdByName(String name) {
        List <University> univers = simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM University WHERE name = ?",
                   new Object[]{name},
                   new int[]{Types.VARCHAR},
                   UniversityMapper);
        if (univers.size() == 0){
            return 0;
        }
        return univers.get(0).getId();
    }
    
}

