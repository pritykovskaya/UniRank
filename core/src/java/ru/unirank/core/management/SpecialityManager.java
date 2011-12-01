package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.SpecialityMapper;
import ru.unirank.core.tables.Speciality;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 13.11.2011
 * Time: 16:48:24
 */
public class SpecialityManager {
    

    private static final Logger log = Logger.getLogger(SpecialityManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private SpecialityMapper SpecialityMapper;

    public SpecialityManager (DataSource dataSource){
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.SpecialityMapper = new SpecialityMapper();
    }
    public SpecialityManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.SpecialityMapper = new SpecialityMapper();
    }
    public void cleanDataStore() {
        try{
        simplejdbcTemplate.update("TRUNCATE TABLE Speciality");
        }catch (DataAccessException ex)
        {
              log.error("error in cleaning Data");
        }
    }
   public int addSpeciality(Speciality speciality, String name) {
        try {
            List id = simplejdbcTemplate.getJdbcOperations().queryForList("SELECT id from Speciality", Integer.class);
            int lastId = 1;
            if( id.size() > 0){
                 lastId = (Integer)(id.get(id.size()-1)) + 1;
            }
            DirectionManager direct = new DirectionManager(simplejdbcTemplate);
            if(direct.getDirectionIdByName(name) == 0){
                System.out.println("The foreign key does not exist");
                return 0;
            }
            if( this.getSpecialityIdByName(speciality.getName()) > 0){
                log.error("Such Speciality already exist");
                return this.getSpecialityIdByName(speciality.getName());
            }
            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO Speciality (id, direction_id, name) VALUES(?,?,?)",
                    new Object[]{lastId, direct.getDirectionIdByName(name),  speciality.getName()},
                    new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR});
             return lastId;
        } catch (DataAccessException e) {
                log.error("cannot add Speciality");
        }
        return 0;
    }
       
        public void addListOfSpeciality (List<Speciality> Faculties, String name) {
        for (Speciality fac : Faculties) {
            addSpeciality(fac, name);
        }
    }
     public List<Speciality> getAllSpecialities() {
        List<Speciality> specialfaculties =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Speciality", SpecialityMapper);
     return specialfaculties;
    }

    public Speciality getSpecialityById(int id){
        List <Speciality> ranking =  simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Speciality WHERE id=?",
                new Object[] {id},
                new int[]{Types.INTEGER},
                SpecialityMapper);
        return ranking.get(0);
    }

    public List<Integer> getListOfSpecialityIdByDirectionId(int direction_id){
        List <Integer> specialities_id =  simplejdbcTemplate.getJdbcOperations().query("SELECT id FROM Speciality WHERE direction_id=?",
                new Object[] {direction_id},
                new int[]{Types.INTEGER},
                SpecialityMapper);
        return specialities_id;
    }

    public int getSpecialityIdByName(String name) {
        List <Speciality> special = simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM Speciality WHERE name = ?",
                   new Object[]{name},
                   new int[]{Types.VARCHAR},
                   SpecialityMapper);
        if (special.size() == 0){
            return 0;
        }
        return special.get(0).getId();
    }
}
