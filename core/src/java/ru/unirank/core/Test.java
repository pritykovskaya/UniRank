package ru.unirank.core;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.unirank.core.management.*;
import ru.unirank.core.tables.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 13.11.2011
 * Time: 18:40:14
 */
public class Test {

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/UNIINFO");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        DirectionManager direction = new DirectionManager(dataSource);
        RankingResultManager result = new RankingResultManager(dataSource);
        RankingRawDescriptionManager rawInfo = new RankingRawDescriptionManager(dataSource);
        SpecialityManager special = new SpecialityManager(dataSource);
        SpecialityFacultyManager specfak = new SpecialityFacultyManager(dataSource);
        UniversityManager univers = new UniversityManager(dataSource);
        FacultyManager facultet = new FacultyManager(dataSource);
        RankingMethodManager method = new RankingMethodManager(dataSource);        
        // direction.cleanDataStore();
        direction.addDirection(new Direction(5, "qdhgdfe"));
        int a;
        special.addSpeciality(new Speciality("Speciality1"), "qdhgdfe");
        //univers.cleanDataStore();
        univers.addUniversity(new University(-3, "SPBGPU", "piter", "asfwfsf"));
        univers.addUniversity(new University(5, "SPBGU", "spb", "eagfds"));
        //facultet.cleanDataStore();
        a = facultet.addFaculty(new Faculty("number1", "description1"), "SPBGU");
        facultet.addFaculty(new Faculty("number2", "description2"), "SPBGPU");
        specfak.cleanDataStore();
        specfak.addSpecialityFaculty("number2", "SPBGU", "Speciality1");
        specfak.addSpecialityFaculty("number1", "SPBGPU", "Speciality1");
        rawInfo.cleanDataStore();
        rawInfo.addRankingRawDescription(new RankingRawDescription("rwsjtdydj"));
        rawInfo.addRankingRawDescription(new RankingRawDescription("ehgf"));
        result.cleanDataStore();
        method.cleanDataStore();
        method.addRankingMethod(new RankingMethod(1, "DJTG", 1), "qdhgdfe");
        method.addRankingMethod(new RankingMethod(3, "qwer", 2), "qdhgdfe");
        result.cleanDataStore();
        result.addRankingResult(new RankingResult(1, 2.5), 1, "number1","SPBGU");
        result.addRankingResult(new RankingResult(1, 2.5), 2, "number2","SPBGPU");



        System.out.println(a);
    }
}
