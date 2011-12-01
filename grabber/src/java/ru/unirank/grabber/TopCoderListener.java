package ru.unirank.grabber;

import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.unirank.core.management.*;
import ru.unirank.core.tables.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pritykovskaya
 * Date: 16.10.11
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class TopCoderListener implements ScraperRuntimeListener {

    DriverManagerDataSource dataSource;
    DirectionManager direction;
    RankingResultManager result;
    RankingRawDescriptionManager rawInfo;
    SpecialityManager special;
    RankingMethodManager method;
    SpecialityFacultyManager specfak;
    UniversityManager univers;
    FacultyManager facultet;
    TableOfFactsManager table;

    public TopCoderListener() {
        this.dataSource = new DriverManagerDataSource();
        this.dataSource.setUrl("jdbc:mysql://localhost/UNIINFO");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("Mat-mex2012");
        this.direction = new DirectionManager(dataSource);
        this.result = new RankingResultManager(dataSource);
        this.rawInfo = new RankingRawDescriptionManager(dataSource);
        this.special = new SpecialityManager(dataSource);
        this.method = new RankingMethodManager(dataSource);
        this.specfak = new SpecialityFacultyManager(dataSource);
        this.univers = new UniversityManager(dataSource);
        this.facultet = new FacultyManager(dataSource);
        this.table = new TableOfFactsManager(dataSource);
    }

    public void onExecutionStart(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionPaused(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionContinued(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onNewProcessorExecution(Scraper scraper, BaseProcessor baseProcessor) {
        if ("empty".equalsIgnoreCase(scraper.getRunningProcessor().getElementDef().getShortElementName())) {
            Variable universityName = (Variable) scraper.getContext().get("universityName");

            if (universityName != null && !universityName.toString().equals("")) {

                Variable universityCountry = (Variable) scraper.getContext().get("universityCountry");

                univers.addUniversity(new University(universityName.toString(), universityCountry.toString(), "description"));
                facultet.addFaculty(new Faculty("mathematics", ""), universityName.toString());
                direction.addDirection(new Direction("mathematics science"));
                special.addSpeciality(new Speciality("applied mathematics"), "mathematics science");
                specfak.addSpecialityFaculty("mathematics", universityName.toString(), "applied mathematics");
                rawInfo.addRankingRawDescription(new RankingRawDescription("TopCoder rating"));
                method.addRankingMethod(new RankingMethod(3, "Implement!", 1), "mathematics science");
                Variable universityRank = (Variable) scraper.getContext().get("universityRank");
                Variable universityRawInfo = (Variable) scraper.getContext().get("universityRating");
                result.addRankingResult(new RankingResult(universityRank.toInt(), universityRawInfo.toDouble()), 1, "mathematics", universityName.toString());
            }
        }
    }

    public void cleanAllData() {
        table.cleanDataStore();
        specfak.cleanDataStore();
        result.cleanDataStore();
        method.cleanDataStore();
        rawInfo.cleanDataStore();
        facultet.cleanDataStore();
        univers.cleanDataStore();
        special.cleanDataStore();
        direction.cleanDataStore();
    }

    public void onExecutionEnd(Scraper scraper) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onProcessorExecutionFinished(Scraper scraper, BaseProcessor baseProcessor, Map map) {
        // System.out.println(map);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onExecutionError(Scraper scraper, Exception e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
