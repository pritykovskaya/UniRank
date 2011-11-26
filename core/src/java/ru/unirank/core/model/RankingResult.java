package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
public class RankingResult
{
    private int method_id;
    private int faculty_id;
    private int rank;

    public RankingResult(int rank)
    {
        //this.method_id = method_id;
        //this.faculty_id = faculty_id;
        this.rank = rank;
    }
    public int getMethod_id()
    {
        return method_id;
    }

    public int fetFaculty_id()
    {
        return faculty_id;
    }

    public int getRank()
    {
        return rank;
    }
}
