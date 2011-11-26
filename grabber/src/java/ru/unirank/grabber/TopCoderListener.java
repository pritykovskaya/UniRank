package ru.unirank.grabber;

import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperRuntimeListener;
import org.webharvest.runtime.processors.BaseProcessor;
import org.webharvest.runtime.variables.Variable;
import ru.unirank.core.db.Database;
import ru.unirank.core.model.*;

import java.sql.SQLException;
import java.util.Map;

public class TopCoderListener implements ScraperRuntimeListener {

    Database database;

    public TopCoderListener(Database database) {
        this.database = database;
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

            if ( universityName != null && !universityName.toString().equals("") ) {

//                String uni_city = universityName.toString();
//                if (uni_city.indexOf(" ") != -1) {
//                    uni_city = uni_city.substring(0, uni_city.indexOf(" "));
//                }

                Variable universityCountry = (Variable) scraper.getContext().get("universityCountry");

                University uni = new University(universityName.toString(), universityCountry.toString(), "");

                try {
                    database.addUniversity(uni);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Faculty faculty = new Faculty("mathematics", "");

                try {
                    database.addFaculty(faculty);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

               SpecialityFaculty spec_fac = new SpecialityFaculty(1,0);

                try {
                    database.addSpeciality_faculty(spec_fac);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Variable universityRank = (Variable) scraper.getContext().get("universityRank");
                int uniRank = universityRank.toInt();

                //System.out.println(uniRank);
                RankingResult ranking_result = new RankingResult(uniRank);

                try {
                    database.addRanking_result(ranking_result);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Variable universityRawInfo = (Variable) scraper.getContext().get("universityRating");
                double uniRawInfo = universityRawInfo.toDouble();

                //System.out.println(uniRawInfo);
                RankingRawInfoResult ranking_rawInfoResult = new RankingRawInfoResult(uniRawInfo);

                try {
                    database.addRanking_raw_info_result(ranking_rawInfoResult);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }

        }
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