package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.FacultyMapper;
import ru.unirank.core.tables.Faculty;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
  
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 31.10.2011
 * Time: 16:48:32
 */
public class FacultyManager { 

    private static final Logger log = Logger.getLogger(FacultyManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private FacultyMapper FacultyMapper;

    public FacultyManager (DataSource dataSource){
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.FacultyMapper = new FacultyMapper();
    }
    public FacultyManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.FacultyMapper = new FacultyMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE Faculty");
        }catch (DataAccessException ex)
        {
              log.error("error in cleaning Data");
        }
    }
   public int addFaculty(Faculty Faculty, String name) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from Faculty", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
            UniversityManager univers = new UniversityManager(simplejdbcTemplate);
            if(univers.getUniversityIdByName(name) == 0){
                log.error("The foreign key does not exist");
                return 0;
            }
           /* if( this.getFacultyIdByName(Faculty.getName()) > 0){
                System.out.println("Such Faculty already exist");
                return this.getFacultyIdByName(Faculty.getName());
            } */
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO Faculty (id, university_id, name, description) VALUES(?,?,?,?)",
                    new Object[]{lastId, univers.getUniversityIdByName(name), Faculty.getName(), Faculty.getDescription()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR});
             return lastId;
        } catch (DataAccessException e) {
                log.error("cannot add Faculty");
        }
        return 0;
    }
       
        public void addListOfFaculty (List<Faculty> faculties, String name) {
        for (Faculty fac : faculties) {
            addFaculty(fac, name);
        }
    }
     public List<Faculty> getAllFaculties() {
        List<Faculty> faculties =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Faculty", FacultyMapper);
     return faculties;
    }

    public Faculty getFacultyById(int Faculty_id) {
        List<Faculty> faculties =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Faculty WHERE id = ?",
                        new Object[]{Faculty_id},
                        new int[]{Types.INTEGER},
                        FacultyMapper);
        if (faculties.size() > 0) {
            return faculties.get(0);
        }
        return null;
    }


    public int getFacultyIdByName(String name) {
        List <Faculty> facult = simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Faculty WHERE name = ?",
                   new Object[]{name},
                   new int[]{Types.VARCHAR},
                   FacultyMapper);
        if (facult.size() == 0){
            return 0;
        }
        return facult.get(0).getId();
    }

    public List<Integer> getListOfFacultyIdByUniversityId(int uni_id) {
        List <Integer> facult = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id FROM Faculty WHERE university_id = ?",
                   new Object[]{uni_id},
                   new int[]{Types.INTEGER},
                   Integer.class);
        return facult;
    }

    public List<Integer> getListOfUniversityIdByFacultyName(String faculty_name) {
        List <Integer> facult = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT university_id FROM Faculty WHERE name = ?",
                   new Object[]{faculty_name},
                   new int[]{Types.VARCHAR},
                   String.class);
        return facult;
    }

    public List<Faculty> getListOfFacultyByFacultyName(String faculty_name) {
        List <Faculty> facult = simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Faculty WHERE name = ?",
                   new Object[]{faculty_name},
                   new int[]{Types.VARCHAR},
                   FacultyMapper);
        return facult;
    }
}
