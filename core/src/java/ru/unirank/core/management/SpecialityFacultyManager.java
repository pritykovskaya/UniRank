package ru.unirank.core.management;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.unirank.core.mapper.SpecialityFacultyMapper;
import ru.unirank.core.tables.Faculty;
import ru.unirank.core.tables.SpecialityFaculty;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 13.11.2011
 * Time: 17:06:23
 */
public class SpecialityFacultyManager {

    private static final Logger log = Logger.getLogger(SpecialityFacultyManager.class);

    private SimpleJdbcTemplate simplejdbcTemplate;
    private SpecialityFacultyMapper SpecialityFacultyMapper;

    public SpecialityFacultyManager(DataSource dataSource) {
        this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
        this.SpecialityFacultyMapper = new SpecialityFacultyMapper();
    }

    public SpecialityFacultyManager(SimpleJdbcTemplate jdbcTemplate) {
        this.simplejdbcTemplate = jdbcTemplate;
        this.SpecialityFacultyMapper = new SpecialityFacultyMapper();
    }

    public void cleanDataStore() {
        try {
            simplejdbcTemplate.update("TRUNCATE TABLE SpecialityFaculty");
        } catch (DataAccessException ex) {
            log.error("error in cleaning Data");
        }
    }

    public void addSpecialityFaculty(String faculty_name, String university_name, String special) {
        try {
            SpecialityManager speciality = new SpecialityManager(simplejdbcTemplate);
            FacultyManager faculty = new FacultyManager(simplejdbcTemplate);
            UniversityManager university = new UniversityManager(simplejdbcTemplate);
            if (speciality.getSpecialityIdByName(special) == 0 || faculty.getFacultyIdByName(faculty_name) == 0) {
                System.out.println("The foreign key does not exist");
                return;
            }
            int facultet = 0;
            for(Faculty facult : faculty.getListOfFacultyByFacultyName(faculty_name)){
                if (facult.getUniversityId() == university.getUniversityIdByName(university_name))
                    facultet = facult.getId();
            }

            simplejdbcTemplate.getJdbcOperations().update("INSERT INTO SpecialityFaculty (speciality_id, faculty_id) VALUES(?,?)",
                    new Object[]{speciality.getSpecialityIdByName(special), facultet},
                    new int[]{Types.INTEGER, Types.INTEGER});
            return;
        } catch (DataAccessException e) {
            System.out.println(e);
            //log.error("cannot add SpecialityFaculty");
        }
    }

    public void addListOfSpecialityFaculty(List<String> specialities, String university_name, List<String> faculties) {
        for (String spec : specialities) {
            for (String fac : faculties) {
                addSpecialityFaculty(spec, university_name, fac);
            }
        }
    }
    public List<Integer> findAllSpecialityIdByFacultyId(int faculty_id){
          return simplejdbcTemplate.getJdbcOperations().queryForList("SELECT speciality_id FROM SpecialityFaculty WHERE faculty_id=?",
                  new Object[] {faculty_id}, Integer.class);
    }
    public List<Integer> findAllFacultyyIdBySpecialityId(int speciality_id){
          return simplejdbcTemplate.getJdbcOperations().queryForList("SELECT faculty_id FROM SpecialityFaculty WHERE speciality_id=?",
                  new Object[] {speciality_id}, Integer.class);
    }
    public List<SpecialityFaculty> getAllSpecialityFaculties() {
        List<SpecialityFaculty> faculties =
                simplejdbcTemplate.getJdbcOperations().query("SELECT * FROM SpecialityFaculty", SpecialityFacultyMapper);
        return faculties;
    }

}
