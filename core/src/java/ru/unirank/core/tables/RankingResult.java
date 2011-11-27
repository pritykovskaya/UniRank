package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class RankingResult
{
    private int id;
    private int method_id;
    private int faculty_id;
    private int rank;
    private int ranking_raw_info_description_id;
    private double value;

    public RankingResult(int id, int method_id, int faculty_id, int rank, int ranking_raw_info_description_id, double value)
    {
        this.id = id;
        this.method_id = method_id;
        this.faculty_id = faculty_id;
        this.rank = rank;
        this.ranking_raw_info_description_id = ranking_raw_info_description_id;
        this.value = value;
    }

    public RankingResult(int rank, double value)
    {
        this.id = 0;
        this.method_id = 0;
        this.faculty_id = 0;
        this.rank = rank;
        this.ranking_raw_info_description_id = 0;
        this.value = value;
    }

    public int getId()
    {
        return id;
    }

    public int getMethodId()
    {
        return method_id;
    }

    public int getFacultyId()
    {
        return faculty_id;
    }

    public int getRank()
    {
        return rank;
    }
    public int getRankingRawInfoDescriptionId()
    {
        return ranking_raw_info_description_id;
    }

    public double getValue()
    {
        return value;
    }

    public void setRank(int rank){
        this.rank = rank;
    }
}