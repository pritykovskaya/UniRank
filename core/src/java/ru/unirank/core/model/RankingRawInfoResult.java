package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
public class RankingRawInfoResult
{

    private int faculty_id;
    private int  ranking_raw_info_description_id;
    private double value;

    public RankingRawInfoResult(double value)
    {
        //this.faculty_id = faculty_id;
        //this.ranking_raw_info_description_id = ranking_raw_info_description_id;
        this.value = value;
    }
    public int getFaculty_id()
    {
        return faculty_id;
    }

    public int getRanking_raw_info_description_id()
    {
        return ranking_raw_info_description_id;
    }

    public double getValue()
    {
        return value;
    }

}