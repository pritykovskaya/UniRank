package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
public class RankingRawInfoDescription
{

    private int method_id;
    private String description;

    public RankingRawInfoDescription(int method_id, String description)
    {

        this.method_id = method_id;
        this.description = description;
    }
    public int getMethod_id()
    {
        return method_id;
    }

    public String getDescription()
    {
        return description;
    }
}